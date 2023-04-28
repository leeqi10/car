package com.xhu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhu.entity.Orderplus;
import org.apache.ibatis.annotations.Mapper;

/**
* @author tq
* @description 针对表【orderplus】的数据库操作Mapper
* @createDate 2023-04-24 21:50:07
* @Entity com.xhu.entity.Orderplus
*/
@Mapper
public interface OrderplusMapper extends BaseMapper<Orderplus> {

    int updateCode(int id,String code);
}




