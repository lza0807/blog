package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.enums.StateEnums;
import com.lza.blog.pojo.Type;
import com.lza.blog.service.TypeService;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 博客分类
 * @Author: liuzian
 * @Date: Create in 15:12 2020/3/29
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 添加类型
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Type type) {
        if (type == null || StringUtils.isBlank(type.getTypeName())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "类型名称必填!");
        }
        typeService.save(type);
        return new Result<>("添加成功!");
    }

    /**
     * 查询所有类型--后台
     *
     * @return
     */
    @RequestMapping(value = "/listBack", method = RequestMethod.GET)
    public Result<List<Type>> getAllType() {
        List<Type> typeList = typeService.getAll();
        return new Result<>(typeList);
    }

    /**
     * 查询所有类型--前台
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<List<Type>> getList() {
        List<Type> typeList = typeService.getList();
        return new Result<>(typeList);
    }
    /**
     * 根据ID查询分类信息
     */
    @RequestMapping(value = "/getTypeInfoById/{id}", method = RequestMethod.GET)
    public Result<Type> getTypeInfoById(@PathVariable Integer id) {
        Type type = typeService.getTypeInfoById(id);
        return new Result<>(type);
    }

    /**
     * 根据ID更新分类
     */
    @RequestMapping(value = "/updateTypeById", method = RequestMethod.PUT)
    public Result<Object> updateTypeById(@RequestBody Type type) {
        typeService.updateTypeById(type);
        return new Result<>("更新成功!");

    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/isEnable/{id}", method = RequestMethod.PUT)
    public Result<Object> isEnable(@PathVariable Integer id) {
        typeService.isEnable(id);
        return new Result<>("已启用!");
    }

    /**
     * 禁用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id) {
        typeService.disable(id);
        return new Result<>("已禁用!");
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> deleteTypeById(@PathVariable Integer id) {
        typeService.deleteTypeById(id);
        return new Result<>("删除成功");
    }
}
