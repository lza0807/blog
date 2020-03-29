package com.lza.blog.service;


import com.lza.blog.pojo.Link;

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
public interface LinkService {

    /**
     * 保存
     * @param link
     */
    void save(Link link);
}
