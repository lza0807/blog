package com.lza.blog.mapper;


import com.lza.blog.pojo.Log;
import org.springframework.stereotype.Component;

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
}
