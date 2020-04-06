package com.lza.blog.service;


import com.lza.blog.pojo.User;
import com.lza.blog.utils.Page;

/**
 * <p>
 * 用户表服务层接口
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
public interface UserService {


    /**
     * 保存
     * @param user
     */
    void save(User user);

    /**
     * 修改
     * @param user
     * @return
     */
    void update(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<User> getByPage(Page<User> page);
}
