package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.enums.StateEnums;
import com.lza.blog.pojo.User;
import com.lza.blog.service.UserService;
import com.lza.blog.token.UsernamePasswordToken;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 音乐
 * @Author: liuzian
 * @Date: Create in 22:58 2020/3/31
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加
     * @param user
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody User user){
        userService.save(user);
        return new Result<>("添加成功！");
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody User user){
        userService.update(user);
        return new Result<>("修改成功！");
    }
    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public Result<Object> updateInfo(@RequestBody User user) {
        userService.updateInfo(user);
        return new Result<>("修改成功！");
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<User> getById(@PathVariable Integer id){
         User User = userService.getById(id);
        return new Result<>(User);
    }


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        userService.delete(id);
        return new Result<>("删除成功!");
    }


    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
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
        page = this.userService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 重置密码
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/resetPwd",method = RequestMethod.PUT)
    public Result<Object> resetPwd(@RequestBody List<Integer> userIds){
        userService.resetPwd(userIds);
        return new Result<>("密码重置成功!");
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<Object> register(@RequestBody User user){
        Result<Object> result = userService.register(user);
        return result;
    }

    /**
     * 登录用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<Object> login(@RequestBody User user){
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(user.getUsername(), user.getPassword(), StateEnums.USER.getCode());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        User u = (User) subject.getPrincipal();
        u.setPassword("");
        u.setDeleted(null);
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        returnMap.put("user", u);
        return new Result<>(returnMap);
    }
    /**
     * 查询当前用户的评论数和收藏数
     * @return
     */
    @RequestMapping(value = "/commentAndCollectionCount", method = RequestMethod.GET)
    public Result<Map<String, Object>> commentAndCollectionCount() {
        Map<String, Object> countMap = userService.getCommentAndCollectionCount();
        return new Result<>(countMap);
    }

}
