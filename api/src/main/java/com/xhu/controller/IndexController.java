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

@Api(tags = "首页模块")
@RestController
@CrossOrigin
public class IndexController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SendSms sendSms;
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ResponseBody
    /**
     * @params passenger 用户表
     * @return Result 返回的结果解析
     */
    public Result login(@RequestBody Passenger passenger) throws Exception {
        String username=passenger.getUser();
        String password=passenger.getPassword();
        Passenger user=passengerService.LoginByUser(username,password);
        Map<String,Object> map = new HashMap<>();
        if (user!=null){
            String token = JwtUtil.createJWT(String.valueOf(user.getUser()), 60 * 60 *1000L);
            redisCache.setCacheObject("login:"+user.getUser(), user,100, TimeUnit.HOURS);
            int code;
            code= (int) (Math.random()*9+1);
            sendSms.setCode(code);
            sendSms.send();
            map.put("token",token);
            map.put("user",user);
            return new Result(ResponseStatusEnum.SUCCESS,map);
        }
        else {
            return new Result(ResponseStatusEnum.FAILED);
        }
    }
}