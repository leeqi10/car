package com.xhu.mapper;

import com.xhu.entity.Passenger;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author tq
* @description 针对表【passenger】的数据库操作Mapper
* @createDate 2023-04-11 22:24:06
* @Entity com.xhu.entity.Passenger
*/
@Mapper
public interface PassengerMapper extends BaseMapper<Passenger> {
    int updatePlacePassenger(String longitude,String latitude,String user);
}




