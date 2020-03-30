package com.lza.blog.service.impl;


import com.lza.blog.mapper.AdminMapper;
import com.lza.blog.pojo.Admin;
import com.lza.blog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 管理员表服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    /**
     * 获取登录用户
     * @param username
     * @return
     */
    @Override
    public Admin getUserByName(String username) {
        Admin admin =adminMapper.getUserByName(username);
        return admin;
    }

    @Override
    public Admin getAdminInfo() {

        return this.adminMapper.getAdminInfo();
    }

    @Override
    public void updateAdminInfo(Admin admin) {
        this.adminMapper.updateAdminInfo(admin);
    }

    @Override
    public void updatePassword(Admin admin) {
        Admin adminInfo = this.adminMapper.getAdminInfo();
        adminInfo.setPassword(admin.getPassword());
        this.adminMapper.updateAdminInfo(adminInfo);
    }
}
