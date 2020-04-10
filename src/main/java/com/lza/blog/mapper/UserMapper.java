package com.lza.blog.mapper;


import com.lza.blog.pojo.User;
import com.lza.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户表Mapper
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface UserMapper{

    /**
     * 保存
     * @param user
     */
    void save(User user);

    /**
     * 修改
     * @param user
     */
    void update(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页
     * @param page
     * @return
     */
    List<User> getByPage(Page<User> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<User> page);

    /**
     * 根据ids查询所有用户
     * @param userIds
     * @return
     */
    List<User> getUserByIds(List<Integer> userIds);

    /**
     * 是否存在
     * @param username
     * @return
     */
    User isNotExist(String username);
}
