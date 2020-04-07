package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.pojo.Blog;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.StringUtils;
import com.lza.blog.vo.BlogVo;
import com.lza.blog.service.BlogService;
import com.lza.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<Object> get(@PathVariable String id){
        Blog blog = this.blogService.getBlogInfoById(id);
        return new Result<>(blog);

    }

    /**
     * 根据id更新博客
     * @param blog
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Result<Object> updateBlogInfoById(@RequestBody Blog blog){
        this.blogService.updateBlogInfoById(blog);
        return new Result<>("更新成功!");
    }

    /**
     * 根据id阅读博客
     * @param id
     * @return
     */
    @RequestMapping(value = "/read/{id}",method = RequestMethod.GET)
    public Result<Object> readById(@PathVariable String id){
        BlogVo blog = this.blogService.readById(id);
        return new Result<>(blog);
    }

    /**
     * 根据id删除博客
     * @param id id
     * @return  Result<Object>
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable String id){
        this.blogService.delete(id);
        return new Result<>("删除成功!");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)){
            //排序列不为空
            String[] sortColumns = {"blog_goods","blog_read","blog_collection","type_name","blog_comment","created_time","update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序参数不合法!");
            }
        }
        page = this.blogService.getByPage(page);
        return new Result<>(page);
    }

}
