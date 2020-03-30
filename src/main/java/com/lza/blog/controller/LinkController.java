package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.pojo.Link;
import com.lza.blog.service.LinkService;
import com.lza.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 保存
     * @param link
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Link link){
        this.linkService.save(link);
        return new Result<>(ResultEnum.SUCCESS.getCode(),"添加成功!");
    }

    /**
     * 修改友情链接
     * @param link
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> updateById(@RequestBody Link link){
        this.linkService.updateById(link);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/getLinkInfoById/{id}",method = RequestMethod.GET)
    public Result<Object> getLinkInfoById(@PathVariable Integer id){
        Link link = this.linkService.getLinkInfoById(id);
        return new Result<>(link);
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = "/getAllLinkInfo",method = RequestMethod.GET)
    public Result<List<Link>> getAllLinkInfo(){
        List<Link> links = this.linkService.getAllLinkInfo();
        return new Result<>(links);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        this.linkService.delete(id);
        return new Result<>("删除成功！");
    }

}
