package com.lza.blog.service.impl;


import com.lza.blog.enums.ResultEnum;
import com.lza.blog.enums.StateEnums;
import com.lza.blog.exception.BlogException;
import com.lza.blog.mapper.TypeMapper;
import com.lza.blog.pojo.Type;
import com.lza.blog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;


    /**
     * 添加分类
     * @param type
     */
    @Override
    public void save(Type type) {
        //根据类别名称查询是否存在
        Type isType = typeMapper.isExistType(type.getTypeName());
        if (isType!=null){
            throw new BlogException("该分类已存在!");
        }
        typeMapper.save(type);
    }

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    @Override
    public List<Type> getList() {
        return typeMapper.getList();
    }

    @Override
    public int updateTypeById(Type type) {
        return typeMapper.updateTypeById(type);
    }

    @Override
    public void isEnable(Integer id) {
        //根据ID查询到对应的类别
        Type type = this.typeMapper.isEnable(id);
        type.setEnable(StateEnums.ENABLED.getCode());
        this.typeMapper.updateTypeById(type);
    }


    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @Override
    public void deleteTypeById(Integer id) {
        this.typeMapper.deleteTypeById(id);
    }

    /**
     * 状态禁用
     * @param id
     */
    @Override
    public void disable(Integer id) {
        Type type = this.typeMapper.isEnable(id);
        type.setEnable(StateEnums.NOT_ENABLE.getCode());
        this.typeMapper.updateTypeById(type);
    }

    @Override
    public Type getTypeInfoById(Integer id) {
        return this.typeMapper.getTypeInfoById(id);
    }
}
