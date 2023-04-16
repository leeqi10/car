package com.xhu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhu.entity.Passenger;
import com.xhu.service.PassengerService;
import com.xhu.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author tq
* @description 针对表【passenger】的数据库操作Service实现
* @createDate 2023-04-11 22:25:58
*/
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger>
    implements PassengerService{
    @Autowired
    private  PassengerMapper passengerMapper;
    @Override
    public Passenger LoginByUser(String user, String password) {
        LambdaQueryWrapper<Passenger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Passenger::getUser,user);
        Passenger passenger = passengerMapper.selectOne(lambdaQueryWrapper);
        if (passenger==null){
            return null;
        }
        if (passenger.getPassword().equals(password)){
            return passenger;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean registerByUser(String user, String password) {
        LambdaQueryWrapper<Passenger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Passenger::getUser,user);
        Passenger passenger = passengerMapper.selectOne(lambdaQueryWrapper);
        if (passenger!=null){
            return false;
        }
        else{
            passenger.setUser(user);
            passenger.setPassword(password);
            passengerMapper.insert(passenger);
            return true;
        }
    }
}




