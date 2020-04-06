package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.pojo.User;
import com.lza.blog.service.UserService;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 音乐
 * @Author: liuzian
 * @Date: Create in 22:58 2020/3/31
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService UserService;

    /**
     * 添加
     * @param user
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody User user){
        UserService.save(user);
        return new Result<>("添加成功！");
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody User user){
        UserService.update(user);
        return new Result<>("修改成功！");
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<User> getById(@PathVariable Integer id){
         User User =UserService.getById(id);
        return new Result<>(User);
    }


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        UserService.delete(id);
        return new Result<>("删除成功!");
    }


    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.GET)
    public Result<Page<User>> getByPage(@RequestBody Page<User> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)){
            //排序列不为空
            String[] sortColumns = {"sex","created_time","update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序参数不合法!");
            }
        }
        page = this.UserService.getByPage(page);
        return new Result<>(page);
    }

}
