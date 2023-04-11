package com.xhu.service;

import com.xhu.entity.Passenger;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author tq
* @description 针对表【passenger】的数据库操作Service
* @createDate 2023-04-11 22:25:58
*/
public interface PassengerService extends IService<Passenger> {
    public Passenger LoginByUser(String user,String password);

}
