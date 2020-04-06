package com.lza.blog.service.impl;


import com.lza.blog.mapper.LogMapper;
import com.lza.blog.pojo.Log;
import com.lza.blog.service.LogService;
import com.lza.blog.utils.Page;
import com.lza.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Page<BlogVo> getByPage(Page<BlogVo> page) {
        //查询数据
        List<BlogVo> blogVoList = logMapper.getByPage(page);
        page.setList(blogVoList);
        //查询总数
        int totalCount = logMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;

    }

    @Override
    public void delete(Integer id) {
        logMapper.delete(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        logMapper.deleteByIds(ids);
    }
}
