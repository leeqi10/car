package com.xhu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhu.entity.Driver;
import com.xhu.entity.Orderplus;
import com.xhu.mapper.OrderplusMapper;
import com.xhu.service.DriverService;
import com.xhu.mapper.DriverMapper;
import com.xhu.utils.DistanceUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private OrderplusMapper orderplusMapper;
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

    @Override
    public int updatePlaceDriver(Driver driver) {
        //更新自己的位置
        String user = driver.getUser();
        String longitude = driver.getLongitudeform();
        String latitude = driver.getLatitudefrom();
        int i= driverMapper.updateDriverPlace(longitude,latitude,user);

        return i;
    }

    @Override
    public List<Orderplus> orderPlus(Driver driver) {
        LambdaQueryWrapper<Orderplus> orderplusLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderplusLambdaQueryWrapper.eq(Orderplus::getCode,"0");
        //存放待订单列表
        List<Orderplus> orderpluses = orderplusMapper.selectList(orderplusLambdaQueryWrapper);
        //存放订单列表和对应的距离值
        Map<Orderplus,Integer> orderplusStringMap= new HashMap<>();

        for (Orderplus orderplus:orderpluses){
            //计算距离单位米
            if (orderplus.getCallx()==null||orderplus.getCallx().length()==0){
                orderplus.setCallx("0");
            }
            if (orderplus.getCally()==null||orderplus.getCally().length()==0){
                orderplus.setCally("0");
            }
            int number = (int) DistanceUtils.getDistance(Double.parseDouble(orderplus.getCally()),Double.parseDouble(orderplus.getCallx()),Double.parseDouble(driver.getLongitudeform()),Double.parseDouble(driver.getLatitudefrom()));
            orderplusStringMap.put(orderplus,number);
        }
        //排序
        Map<Orderplus,Integer> orderplusStringMapPlus= DistanceUtils.sortMap(orderplusStringMap);

        Set<Orderplus> set = orderplusStringMapPlus.keySet();

        List<Orderplus> list = new ArrayList<>(set);
        return list;
    }

    @Override
    public Map<Orderplus, Integer> orderPlusMap(Driver driver) {
        LambdaQueryWrapper<Orderplus> orderplusLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderplusLambdaQueryWrapper.eq(Orderplus::getCode,"0");
        //存放待订单列表
        List<Orderplus> orderpluses = orderplusMapper.selectList(orderplusLambdaQueryWrapper);
        //存放订单列表和对应的距离值
        Map<Orderplus,Integer> orderplusStringMap= new HashMap<>();

        for (Orderplus orderplus:orderpluses){
            //计算距离单位米
            if (orderplus.getCallx()==null||orderplus.getCallx().length()==0){
                orderplus.setCallx("0");
            }
            if (orderplus.getCally()==null||orderplus.getCally().length()==0){
                orderplus.setCally("0");
            }
            int number = (int) DistanceUtils.getDistance(Double.parseDouble(orderplus.getCally()),Double.parseDouble(orderplus.getCallx()),Double.parseDouble(driver.getLongitudeform()),Double.parseDouble(driver.getLatitudefrom()));
            orderplusStringMap.put(orderplus,number);
        }
        //排序
        Map<Orderplus,Integer> orderplusStringMapPlus= DistanceUtils.sortMap(orderplusStringMap);

        return orderplusStringMapPlus;
    }

    @Override
    public List<Orderplus> orderPlusorder(Driver driver) {
        LambdaQueryWrapper<Orderplus> orderplusLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderplusLambdaQueryWrapper.eq(Orderplus::getCode,"0");
        //存放待订单列表
        List<Orderplus> orderpluses = orderplusMapper.selectList(orderplusLambdaQueryWrapper);

        //存放之后计算好了的值
        List<Orderplus> orderpluses1 = new ArrayList<>();
        for (Orderplus orderplus:orderpluses){
            //计算距离单位米
            if (orderplus.getCallx()==null||orderplus.getCallx().length()==0){
                orderplus.setCallx("0");
            }
            if (orderplus.getCally()==null||orderplus.getCally().length()==0){
                orderplus.setCally("0");
            }
            int number = (int) DistanceUtils.getDistance(Double.parseDouble(orderplus.getCally()),Double.parseDouble(orderplus.getCallx()),Double.parseDouble(driver.getLongitudeform()),Double.parseDouble(driver.getLatitudefrom()));
            //更新里程
            orderplusMapper.updateMilege(orderplus.getId(),String.valueOf(number));
            orderpluses1.add(orderplus);
        }
        //排序
        Collections.sort(orderpluses1,(o1, o2) -> {
            if (o1.getMilege().length()==0){
                o1.setMilege("0");
            }
            if (o2.getMilege().length()==0){
                o2.setMilege("0");
            }

           return Integer.parseInt(o1.getMilege())-Integer.parseInt(o2.getMilege());
        });
        return orderpluses1;
    }
}




