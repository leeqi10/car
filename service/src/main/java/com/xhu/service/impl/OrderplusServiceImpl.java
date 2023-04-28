package com.xhu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhu.entity.Driver;
import com.xhu.entity.Orderplus;
import com.xhu.entity.Passenger;
import com.xhu.mapper.DriverMapper;
import com.xhu.mapper.OrderplusMapper;
import com.xhu.mapper.PassengerMapper;
import com.xhu.service.OrderplusService;
import com.xhu.vo.Orderplusvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderplusServiceImpl extends ServiceImpl<OrderplusMapper, Orderplus>
        implements OrderplusService {
    @Autowired
    private OrderplusMapper orderMapper;
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Override
    @Transactional
    public int insertOrder(Orderplus order) {
        try {
            int sum = orderMapper.insert(order);
            return sum;
        }
        catch (Exception e){

            e.printStackTrace();
            System.out.println("数据库报错");
            return 0;
        }
    }

    @Override
    public Orderplusvo getDetail(String i) {

        // 订单表
        Orderplus orderplus = orderMapper.selectById(i);
        //乘客表
        Passenger passenger = passengerMapper.selectByUser(orderplus.getUserid());
        if (passenger==null){
            passenger = new Passenger();
        }
        //司机表
        Driver driver = driverMapper.selectByUser(orderplus.getDriverid());
        if (driver==null){
            driver = new Driver();
        }
        //建立一个所有信息表
        Orderplusvo orderplusvo = new Orderplusvo(orderplus,passenger,driver);
        return orderplusvo;
    }

    @Override
    public int updateOrderStatus(Orderplus orderplus) {
        int i = orderMapper.updateCode(orderplus.getId(),orderplus.getDriverid(),orderplus.getCode());
        return i;
    }
}
