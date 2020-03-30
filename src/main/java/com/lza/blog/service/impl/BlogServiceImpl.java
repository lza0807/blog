package com.lza.blog.service.impl;


import com.lza.blog.mapper.BlogMapper;
import com.lza.blog.mapper.TypeMapper;
import com.lza.blog.pojo.Blog;
import com.lza.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客表服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void save(Blog blog) {
        this.blogMapper.save(blog);
    }
}
