package com.lza.blog.service;


import com.lza.blog.pojo.Type;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务层接口
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
public interface TypeService {

    /**
     * 添加分类
     * @param type
     */
    void save(Type type);

    /**
     * 查询所有分类
     * @return
     */
    List<Type> getAll();

    /**
     * 查询所有分类==前台
     * @return
     */
    List<Type> getList();

    /**
     * 更新ID更新分类信息
     * @param type
     * @return
     */
    int updateTypeById(Type type);

    /**
     * 是否启用
     * @param id
     */
    void isEnable(Integer id);


    /**
     * 根据ID删除
     * @param id
     * @return
     */
    void deleteTypeById(Integer id);

    /**
     * 状态禁用
     * @param id
     */
    void disable(Integer id);

    /**
     *  根据ID查询分类信息
     */
    Type getTypeInfoById(Integer id);
}
