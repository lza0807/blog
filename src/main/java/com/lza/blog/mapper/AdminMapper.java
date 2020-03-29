package com.lza.blog.mapper;


import com.lza.blog.pojo.Admin;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface AdminMapper{

    /**
     * 获取登录用户
     * @param username
     * @return
     */
    Admin getUserByName(String username);
}
