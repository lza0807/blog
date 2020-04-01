package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.pojo.About;
import com.lza.blog.service.AboutService;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.StringUtils;
import com.lza.blog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 关于 ’我‘
 * @Author: liuzian
 * @Date: Create in 22:58 2020/3/31
 */
@RestController
@RequestMapping("/about")
public class AboutController {
    @Autowired
    private AboutService aboutService;

    /**
     * 添加
     * @param about
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody About about){
        aboutService.save(about);
        return new Result<>("添加成功！");
    }

    /**
     * 修改
     * @param about
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody About about){
        aboutService.update(about);
        return new Result<>("修改成功！");
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<About> get(@PathVariable Integer id){
         About about =aboutService.get(id);
        return new Result<>(about);
    }

    /**
     * 阅读关于我
     * @return
     */
    @RequestMapping(value = "read",method = RequestMethod.GET)
    public Result<About> read(){
        About about =aboutService.read();
        return new Result<>(about);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        aboutService.delete(id);
        return new Result<>("删除成功!");
    }

    /**
     * 启用关于我的
     * @param id
     * @return
     */
    @RequestMapping(value = "/enableById/{id}",method = RequestMethod.PUT)
    public Result<Object> enableById(@PathVariable Integer id){
        aboutService.enableById(id);
        return new Result<>("启用成功!");
    }

    /**
     * 禁用关于我的
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}",method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id){
        aboutService.disable(id);
        return new Result<>("禁用成功!");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.GET)
    public Result<Page<About>> getByPage(@RequestBody Page<About> page){
        page = this.aboutService.getByPage(page);
        return new Result<>(page);
    }

}
