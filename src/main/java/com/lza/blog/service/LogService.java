package com.lza.blog.service;


import com.lza.blog.pojo.Log;

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
}
