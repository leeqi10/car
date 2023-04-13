package com.xhu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xhu.ResponseStatusEnum;
import com.xhu.Result;
import com.xhu.annotation.FilterLogin;
import com.xhu.entity.Passenger;
import com.xhu.mapper.PassengerMapper;
import com.xhu.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "个人信息模块")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private PassengerMapper passengerMapper;
    @ApiOperation(value = "用户个人信息")
    @GetMapping("/info")
    @ResponseBody
    @FilterLogin
    /**
     * @params passenger 用户表
     * @return Result 返回个人信息
     */
    public Result getUserInfo(HttpServletRequest request){
        //获取token
        String token = request.getHeader("token");
        String userId="";
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LambdaQueryWrapper<Passenger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Passenger::getUser,userId);
        Passenger passenger =passengerMapper.selectOne(lambdaQueryWrapper);
        return new Result(ResponseStatusEnum.ok(),passenger);
    }

}
