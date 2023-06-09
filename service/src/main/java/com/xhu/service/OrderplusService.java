package com.xhu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhu.entity.Orderplus;
import com.xhu.vo.Orderplusvo;

/**
* @author tq
* @description 针对表【orderplus】的数据库操作Service
* @createDate 2023-04-24 21:50:07
*/
public interface OrderplusService extends IService<Orderplus> {
    int insertOrder(Orderplus order);

    Orderplusvo getDetail(String i);

    int updateOrderStatus(Orderplus orderplus);
}
