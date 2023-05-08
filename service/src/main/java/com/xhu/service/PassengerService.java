package com.xhu.service;

import com.xhu.entity.Driver;
import com.xhu.entity.Passenger;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
* @author tq
* @description 针对表【passenger】的数据库操作Service
* @createDate 2023-04-11 22:25:58
*/
public interface PassengerService extends IService<Passenger> {
     Passenger LoginByUser(String user,String password);

     boolean registerByUser(String user,String password);

     int updatePlacePassenger(Passenger passenger);

     @Transactional
     double insertOrDeleteMoney(String user,double money);


}
