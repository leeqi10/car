package com.xhu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xhu.utils.ResponseStatusEnum;
import com.xhu.utils.Result;
import com.xhu.annotation.FilterLogin;
import com.xhu.entity.Passenger;
import com.xhu.mapper.PassengerMapper;
import com.xhu.service.PassengerService;
import com.xhu.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Api(tags = "个人信息模块")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private PassengerService passengerService;
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

    @ApiOperation(value = "更新乘客的位置")
    @PostMapping("/updatePlace")
    @ResponseBody
    @FilterLogin
    /**
     * @param passenger 乘客表单
     */
    public Result updatePlace(@RequestBody Passenger passenger) {
        int i = passengerService.updatePlacePassenger(passenger);
        if (i==0){
            return  new Result(ResponseStatusEnum.FAILED,"更新失败");
        }

        return new Result(ResponseStatusEnum.SUCCESS,passenger);
    }
    @ApiOperation(value = "修改余额")
    @PostMapping("/fixMoney")
    @ResponseBody
    @FilterLogin
    public Result insertOrDeleteMoney(String user,Double money){
        if (user==null){
            return new Result(ResponseStatusEnum.FAILED,"请填写用户名称");
        }
        double i = passengerService.insertOrDeleteMoney(user,money);
        if (i<0){
            return new Result(ResponseStatusEnum.FAILED,"用户不存在或者余额不足");
        }
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("user",user);
        map.put("money",i);
        return new Result(ResponseStatusEnum.SUCCESS,map);
    }
}
