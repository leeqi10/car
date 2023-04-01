package com.xhu.service.impl;

import com.xhu.entity.Message;
import com.xhu.mapper.MessageMapper;
import com.xhu.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leeqi10
 * @since 2023-04-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
