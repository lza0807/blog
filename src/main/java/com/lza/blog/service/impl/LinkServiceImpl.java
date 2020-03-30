package com.lza.blog.service.impl;


import com.lza.blog.mapper.CollectionMapper;
import com.lza.blog.mapper.LinkMapper;
import com.lza.blog.pojo.Link;
import com.lza.blog.service.CollectionService;
import com.lza.blog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;


    @Override
    public void save(Link link) {
        this.linkMapper.save(link);
    }

    @Override
    public void updateById(Link link) {
        this.linkMapper.updateByID(link);
    }

    @Override
    public Link getLinkInfoById(Integer id) {
        return this.linkMapper.getLinkInfoById(id);
    }

    @Override
    public List<Link> getAllLinkInfo() {
        return this.linkMapper.getAllLinkInfo();
    }

    @Override
    public void delete(Integer id) {
         this.linkMapper.delete(id);
    }
}
