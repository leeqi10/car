package com.xhu.mapper;

import com.xhu.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author tq
* @description 针对表【order】的数据库操作Mapper
* @createDate 2023-04-11 22:24:06
* @Entity com.xhu.entity.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




