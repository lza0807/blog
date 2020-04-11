package com.lza.blog.realm;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.exception.BlogException;
import com.lza.blog.pojo.Admin;
import com.lza.blog.pojo.User;
import com.lza.blog.service.AdminService;
import com.lza.blog.service.UserService;
import com.lza.blog.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 权限控制的Realm
 * @Author: liuzian
 * @Date: Create in 0:27 2020/3/29
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof Admin) {
            Admin admin = adminService.getUserByName(username);
            if (admin == null) {
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在!");
            }
            return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
        } else {
            User user = userService.getUserByName(username);
            if (user == null || user.getDeleted() == 1) {
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在!");
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }

    }
}
