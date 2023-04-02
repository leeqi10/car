package com.xhu.service;

import com.xhu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.print.DocFlavor;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leeqi10
 * @since 2023-04-01
 */
public interface UserService extends IService<User> {
    User LoginBy(String username,String password);
}
