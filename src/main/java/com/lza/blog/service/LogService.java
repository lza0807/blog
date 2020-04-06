package com.lza.blog.service;


import com.lza.blog.pojo.Log;
import com.lza.blog.utils.Page;
import com.lza.blog.vo.BlogVo;

/**
 * <p>
 * 接口访问日志表服务层接口
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
public interface LogService {


    /**
     * 保存日志
     * @param logger logger
     */
    void logSave(Log logger);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    void deleteByIds(Integer[] ids);
}
