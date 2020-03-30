package com.lza.blog.service;


import com.lza.blog.pojo.Admin;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
public interface AdminService {

    /**
     * 获取登录用户
     * @param username
     * @return
     */
    Admin getUserByName(String username);

    /**
     * 查询管理员信息
     * @return
     */
    Admin getAdminInfo();
    /**
     * 跟新管理员信息
     * @param admin
     * @return
     */
    void updateAdminInfo(Admin admin);

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    void updatePassword(Admin admin);
}
