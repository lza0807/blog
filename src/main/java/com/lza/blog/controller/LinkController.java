package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.pojo.Link;
import com.lza.blog.service.LinkService;
import com.lza.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 友情链接
 * @Author: liuzian
 * @Date: Create in 23:16 2020/3/29
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Link link){

        return new Result<>(ResultEnum.SUCCESS.getCode(),"添加成功!");
    }
}
