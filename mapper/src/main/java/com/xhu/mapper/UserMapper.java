package com.xhu.mapper;

import com.xhu.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leeqi10
 * @since 2023-04-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
