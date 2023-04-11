package com.xhu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhu.entity.Order;
import com.xhu.service.OrderService;
import com.xhu.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author tq
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-04-11 22:25:58
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




