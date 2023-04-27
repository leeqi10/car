package com.xhu.controller;

import com.xhu.ResponseStatusEnum;
import com.xhu.Result;
import com.xhu.annotation.FilterLogin;
import com.xhu.config.SendSms;
import com.xhu.entity.Driver;
import com.xhu.entity.Orderplus;
import com.xhu.service.DriverService;
import com.xhu.service.OrderplusService;
import com.xhu.utils.JwtUtil;
import com.xhu.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(tags = "司机端")
@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SendSms sendSms;
    @Autowired
    private OrderplusService orderplusService;
    @ApiOperation(value = "用户登录")
    @PostMapping("/login/{code}")
    @ResponseBody
    /**
     * @params passenger 用户表
     * @return Result 返回的结果解析
     */
    public Result login(@RequestBody Driver driver, @PathVariable int code) throws Exception {
        String username=driver.getUser();
        String password=driver.getPassword();
        String phone = driver.getTel();
        String jsz = driver.getJsz();
        String carType = driver.getCatype();
        String carno = driver.getCarno();
        //登录
        Driver user=driverService.LoginByUser(username,password);
        if (user!=null&&code==0){
            String token = JwtUtil.createJWT(String.valueOf(user.getUser()), 60 * 60 *1000L);
            redisCache.setCacheObject("login:"+user.getUser(), user,100, TimeUnit.HOURS);
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            map.put("user",user);
            return new Result(ResponseStatusEnum.LOGIN,map);
        }
        if (user==null&&code==0){
            return new Result(ResponseStatusEnum.FAILED,"登录失败");
        }
        //注册
        try {
            int codePlus = redisCache.getCacheObject(phone);
            if (code==codePlus) {
                boolean status = false;
                status = driverService.registerByUser(username, password,jsz,carno,carType,phone);
                if (status) {
                    String token = JwtUtil.createJWT(String.valueOf(driver.getUser()), 60 * 60 *1000L);
                    redisCache.setCacheObject("login:"+driver.getUser(), driver,100, TimeUnit.HOURS);
                    Map<String,Object> map = new HashMap<>();
                    map.put("token",token);
                    map.put("user",driver);
                    return new Result(ResponseStatusEnum.REGISTER,map);
                }
                else {
                    return new Result(ResponseStatusEnum.FAILED,"已有账户");
                }
            }
            else {
                return new Result(ResponseStatusEnum.FAILED,"验证码输入错误");
            }
        }catch (java.lang.IllegalArgumentException e){
            return new Result(ResponseStatusEnum.FAILED,"验证码已经失效");
        }
    }
    @ApiOperation(value = "生成订单")
    @PostMapping("/alterOrder")
    @FilterLogin
    @ResponseBody
    /**
     * @param order 订单表
     */
    public Result insertOrder(@RequestBody Orderplus order){
        //过滤空指针设置为默认
        if (order.getDriverid()==null){
            order.setDriverid("");
        }
        if (order.getStatime()==null){
            order.setStatime(new Date());
            System.out.println(new Date());
        }
        //设置默认值
        //设置状态
        order.setCode("0");
        int i = orderplusService.insertOrder(order);
        if (i==0){
            return new Result(ResponseStatusEnum.FAILED,"创建订单失败，数据库问题");
        }
        return new Result(ResponseStatusEnum.ACCEPT,order);
    }
    @ApiOperation(value = "查询订单详情")
    @GetMapping("/getOrderPlus")
    @ResponseBody
    /**
     * @id 查询订单的id
     */
    public Result selectOrder(String id){
        Orderplus orderplus = orderplusService.getDetail(id);
        if (orderplus==null){
            return new Result(ResponseStatusEnum.FAILED,"不存在此订单");
        }
        return new Result(ResponseStatusEnum.ACCEPT,orderplus);
    }
    @ApiOperation(value = "查询所有待接单的订单")
    @PostMapping("/selectAllOrders")
    @ResponseBody
    /**
     * 查询所有待接单的订单
     */
    public Result selectAllOrders(@RequestBody Driver driver){
        if (driver.getLatitudefrom()==null){
            driver.setLatitudefrom("0");
        }
        if (driver.getLongitudeform()==null){
            driver.setLongitudeform("0");
        }
        List<Orderplus> orderpluses = driverService.orderPlus(driver);
        return new Result(ResponseStatusEnum.SUCCESS,orderpluses);
    }

    @ApiOperation(value = "更新司机的位置")
    @PostMapping("/updatePlace")
    @ResponseBody
    /**
     * @param driver 司机表单
     */
    public Result updatePlace(@RequestBody Driver driver){
        int i =driverService.updatePlaceDriver(driver);
        if (i==0){
            return new Result(ResponseStatusEnum.FAILED,"更新失败");

        }
        return new Result(ResponseStatusEnum.SUCCESS,driver);
    }

}
