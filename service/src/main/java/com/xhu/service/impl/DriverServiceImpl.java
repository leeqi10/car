package com.xhu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhu.entity.Driver;
import com.xhu.service.DriverService;
import com.xhu.mapper.DriverMapper;
import org.springframework.stereotype.Service;

/**
* @author tq
* @description 针对表【driver】的数据库操作Service实现
* @createDate 2023-04-11 22:25:59
*/
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver>
    implements DriverService{

}




