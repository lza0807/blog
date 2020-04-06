package com.lza.blog.mapper;


import com.lza.blog.pojo.Log;
import com.lza.blog.utils.Page;
import com.lza.blog.vo.BlogVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 接口访问日志表Mapper
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface LogMapper  {

    /**
     * 保存日志
     * @param logger
     */
    void logSave(Log logger);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<BlogVo> page);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") Integer[] ids);
}
