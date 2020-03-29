package com.lza.blog.controller;

import com.lza.blog.exception.BlogException;
import com.lza.blog.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * @Description: 项目启动测试
 * @Author: liuzian
 * @Date: Create in 21:08 2020/3/27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试异常
     * @param id id
     * @return Result<Object>
     */
    @RequestMapping(value = "/textException/{id}",method = RequestMethod.GET)
    public Result<Object>  testException(@PathVariable Integer id){
        if (id==1){
            return new Result<>();
        }else {
            throw  new BlogException("发生了异常");
        }
    }
}
