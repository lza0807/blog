package com.lza.blog.service;


import com.lza.blog.pojo.About;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;

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
public interface AboutService {

    /**
     * 添加
     * @param about
     * @return
     */
    void save(About about);

    /**
     * 修改
     * @param about
     */
    void update(About about);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    About get(Integer id);

    /**
     * 阅读关于我
     * @return
     */
    About read();


    /**
     * 根据id删除
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 启用关于我的
     * @param id
     * @return
     */
    void enableById(Integer id);

    /**
     * 禁用关于我的
     * @param id
     * @return
     */
    void disable(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<About> getByPage(Page<About> page);
}
