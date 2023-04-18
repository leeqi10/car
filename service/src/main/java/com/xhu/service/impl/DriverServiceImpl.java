package com.xhu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhu.entity.Driver;
import com.xhu.service.DriverService;
import com.xhu.mapper.DriverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author tq
* @description 针对表【driver】的数据库操作Service实现
* @createDate 2023-04-11 22:25:59
*/
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver>
    implements DriverService{
    @Autowired
    private DriverMapper driverMapper;
    @Override
    public Driver LoginByUser(String user, String password) {
        //查询用户
        LambdaQueryWrapper<Driver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Driver::getUser,user);
        Driver driver = driverMapper.selectOne(lambdaQueryWrapper);

        if (driver==null){
            return null;
        }
        if (password.equals(driver.getPassword())){
            return driver;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean registerByUser(String user, String password, String jsz, String carno, String catype, String tel) {
        //查询用户
        LambdaQueryWrapper<Driver> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Driver::getUser,user);
        Driver driver = driverMapper.selectOne(lambdaQueryWrapper);
        if (driver!=null){
            return false;
        }
        else {
            Driver driver1 = new Driver();
            driver1.setUser(user);
            driver1.setPassword(password);
            driver1.setJsz(jsz);
            driver1.setCarno(carno);
            driver1.setCatype(catype);
            driver1.setTel(tel);
            driverMapper.insert(driver1);
            return true;
        }
    }
}




