package com.lza.blog.service.impl;


import com.lza.blog.mapper.LogMapper;
import com.lza.blog.pojo.Log;
import com.lza.blog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    /**
     * 保存日志
     * @param logger logger
     */
    @Override
    public void logSave(Log logger) {
        logMapper.logSave(logger);
    }
}
