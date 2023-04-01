package com.xhu.service.impl;

import com.xhu.entity.Comment;
import com.xhu.mapper.CommentMapper;
import com.xhu.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
