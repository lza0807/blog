package com.lza.blog.mapper;


import com.lza.blog.pojo.Type;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 帖子类型表Mapper
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface TypeMapper  {

    /**
     * 查询是否存在改分类名称
     * @param typeName
     * @return
     */
    Type isExistType(String typeName);

    /**
     * 保存分类
     * @param type
     */
    void save(Type type);

    /**
     * 查询所有分类
     * @return
     */
    List<Type> getAll();

    /**
     * 查询所有分类--前台
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
    Type isEnable(Integer id);



    /**
     * 根据ID删除
     * @param id
     * @return
     */
    void deleteTypeById(Integer id);

    /**
     * 根据id查询分类信息
     * @param id
     * @return
     */
    Type getTypeInfoById(Integer id);
}
