package com.xhu.controller;

import com.xhu.ResponseStatusEnum;
import com.xhu.Result;
import com.xhu.config.SendSms;
import com.xhu.entity.Driver;
import com.xhu.entity.Passenger;
import com.xhu.service.DriverService;
import com.xhu.service.PassengerService;
import com.xhu.utils.JwtUtil;
import com.xhu.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
}
