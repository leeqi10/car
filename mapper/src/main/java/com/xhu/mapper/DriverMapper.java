package com.xhu.mapper;

import com.xhu.entity.Driver;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author tq
* @description 针对表【driver】的数据库操作Mapper
* @createDate 2023-04-11 22:24:06
* @Entity com.xhu.entity.Driver
*/
@Mapper
public interface DriverMapper extends BaseMapper<Driver> {
    int updateDriverPlace(String longitude,String latitude,String name);

    Driver selectByUser(String user);
}




