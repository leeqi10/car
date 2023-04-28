package com.xhu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhu.entity.Orderplus;
import com.xhu.mapper.OrderplusMapper;
import com.xhu.service.OrderplusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderplusServiceImpl extends ServiceImpl<OrderplusMapper, Orderplus>
        implements OrderplusService {
    @Autowired
    private OrderplusMapper orderMapper;
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
    public Orderplus getDetail(String i) {
        try {
            Orderplus orderplus = orderMapper.selectById(i);
            return orderplus;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库出错");
            return null;
        }
    }

    @Override
    public int updateOrderStatus(Orderplus orderplus) {
        int i = orderMapper.updateCode(orderplus.getId(),orderplus.getDriverid(),orderplus.getCode());
        return i;
    }
}
