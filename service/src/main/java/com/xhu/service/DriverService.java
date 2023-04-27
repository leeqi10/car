package com.xhu.service;

import com.xhu.entity.Driver;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhu.entity.Orderplus;

import java.util.List;

/**
* @author tq
* @description 针对表【driver】的数据库操作Service
* @createDate 2023-04-11 22:25:59
*/
public interface DriverService extends IService<Driver> {
    Driver LoginByUser(String user, String password);

    boolean registerByUser(String user,String password,String jsz,String carno ,String catype,String tel);

    int updatePlaceDriver(Driver driver);

    List<Orderplus> orderPlus(Driver driver);
}
