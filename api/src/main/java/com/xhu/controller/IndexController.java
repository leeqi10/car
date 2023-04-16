package com.xhu.controller;

import com.xhu.ResponseStatusEnum;
import com.xhu.Result;
import com.xhu.config.SendSms;
import com.xhu.entity.Passenger;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags = "首页模块")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class IndexController {
    @Autowired
    private PassengerService passengerService;
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
    public Result login(@RequestBody Passenger passenger,@PathVariable int code) throws Exception {
        String username=passenger.getUser();
        String password=passenger.getPassword();
        String phone = passenger.getTel();
        //登录
        Passenger user=passengerService.LoginByUser(username,password);
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
                status = passengerService.registerByUser(username, password);
                if (status) {
                    String token = JwtUtil.createJWT(String.valueOf(passenger.getUser()), 60 * 60 *1000L);
                    redisCache.setCacheObject("login:"+passenger.getUser(), passenger,100, TimeUnit.HOURS);
                    Map<String,Object> map = new HashMap<>();
                    map.put("token",token);
                    map.put("user",passenger);
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
    @ApiOperation(value = "发送验证码")
    @PostMapping("/code")
    @ResponseBody
    /**
     * @params passenger 用户表
     * @return Result 返回的结果解析
     */
    public Result sendCode(@RequestBody Passenger passenger) throws Exception {
        String tel = passenger.getTel();
        //手机号不能为空
        if (tel==null){
            return new Result(ResponseStatusEnum.FAILED,"手机号不能为空");
        }
        //手机号的正则表达式
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(16[5,6])|(17[0-8])|(18[0-9])|(19[1、5、8、9]))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tel);
        if (!m.matches()){
            new Result(ResponseStatusEnum.FAILED,"手机号不合法");
        }
        //验证码
        int code;
        code= (int) ((Math.random()*9+1)*1000);
        sendSms.send(code,tel);
        //存入redis
        redisCache.setCacheObject(tel,code,1, TimeUnit.HOURS);
        Map<String,Object> map = new HashMap();
        map.put("code",code);
        map.put("passenger",passenger);
        return new Result(ResponseStatusEnum.ok(),map);
    }
}