package com.lza.blog.service.impl;


import com.lza.blog.enums.ResultEnum;
import com.lza.blog.enums.StateEnums;
import com.lza.blog.exception.BlogException;
import com.lza.blog.mapper.AboutMapper;
import com.lza.blog.mapper.CollectionMapper;
import com.lza.blog.pojo.About;
import com.lza.blog.service.AboutService;
import com.lza.blog.service.CollectionService;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import com.lza.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 收藏时间服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutMapper AboutMapper;


    @Override
    public void save(About about) {
        this.AboutMapper.save(about);
    }

    @Override
    public void update(About about) {
        this.AboutMapper.update(about);
    }


    @Override
    public About get(Integer id) {
        return AboutMapper.get(id);
    }

    @Override
    public About read() {
        About about =  AboutMapper.read();
        if (about==null){
            return null;
        }
        about.setAboutRead(about.getAboutRead()+1);
        AboutMapper.update(about);
        return about;
    }

    @Override
    public void delete(Integer id) {
        AboutMapper.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableById(Integer id) {
        //首先查看是否是启用状态
        About read = AboutMapper.read();
        if (read != null){
            throw new BlogException(ResultEnum.ERROR.getCode(),"存在已启用的'关于我的'!");
        }
        About about = AboutMapper.get(id);
        about.setEnable(StateEnums.ENABLED.getCode());
        AboutMapper.enableState(about);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(Integer id) {
        About about = AboutMapper.get(id);
        about.setEnable(StateEnums.NOT_ENABLE.getCode());
        AboutMapper.update(about);
    }

    @Override
    public Page<About> getByPage(Page<About> page) {
        //查询数据
        List<About> aboutList = AboutMapper.getByPage(page);
        page.setList(aboutList);
        //查询总数
        int totalCount = AboutMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
