package com.xhu.controller;

import com.xhu.ResponseStatusEnum;
import com.xhu.Result;
import com.xhu.entity.Passenger;
import com.xhu.service.PassengerService;
import com.xhu.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@Api(tags = "首页模块")
@RestController
@CrossOrigin
public class IndexController {
    @Autowired
    private PassengerService passengerService;
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ResponseBody
    /**
     * @params passenger 用户表
     * @return Result 返回的结果解析
     */
    public Result login(@RequestBody Passenger passenger){
        String username=passenger.getUser();
        String password=passenger.getPassword();
        Passenger user=passengerService.LoginByUser(username,password);
        Map<String,Object> map = new HashMap<>();
        if (user!=null){
            String token = JwtUtil.createJWT(String.valueOf(user.getId()), 60 * 60 *1000L);
            map.put("token",token);
            map.put("user",user);
            return new Result(ResponseStatusEnum.SUCCESS,map);
        }
        else {
            return new Result(ResponseStatusEnum.FAILED);
        }
    }
}