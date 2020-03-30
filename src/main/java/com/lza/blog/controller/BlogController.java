package com.lza.blog.controller;

import com.lza.blog.pojo.Blog;
import com.lza.blog.service.BlogService;
import com.lza.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 18:22 2020/3/30
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 添加博客
     * @param blog
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Blog blog){
        this.blogService.save(blog);
        return new Result<>("添加成功!");
    }
}
