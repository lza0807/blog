package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.enums.StateEnums;
import com.lza.blog.pojo.Admin;
import com.lza.blog.service.AdminService;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.ShiroUtils;
import com.lza.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 登录
 * @Author: liuzian
 * @Date: Create in 23:45 2020/3/28
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Object> login(@RequestBody Admin admin) {
        if (admin == null || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Result<Admin> getUserInfo(){
        Admin loginAdmin = ShiroUtils.getLoginUser();
        return new Result<>(loginAdmin);
    }

    /**
     * 查询管理员信息
     * @return
     */
    @RequestMapping(value = "/getAdmin",method = RequestMethod.GET)
    public Result<Admin> getAdminInfo(){
        Admin admin = this.adminService.getAdminInfo();
        return new Result<>(admin);
    }

    /**
     * 跟新管理员信息
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public Result<Object> updateAdminInfo(@RequestBody Admin admin){
        this.adminService.updateAdminInfo(admin);
        return new Result<>("更新成功");
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.PUT)
    public Result<Object> updatePassword(@RequestBody Admin admin){
        this.adminService.updatePassword(admin);
        return new Result<>("修改成功！");
    }


}
