package com.lza.blog.service.impl;


import com.lza.blog.enums.StateEnums;
import com.lza.blog.mapper.AboutMapper;
import com.lza.blog.mapper.MusicMapper;
import com.lza.blog.pojo.About;
import com.lza.blog.pojo.Music;
import com.lza.blog.service.AboutService;
import com.lza.blog.service.MusicService;
import com.lza.blog.utils.Page;
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
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;


    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }

    @Override
    public Music getById(Integer id) {
        return musicMapper.getById(id);
    }

    @Override
    public void delete(Integer id) {
        musicMapper.delete(id);
    }

    @Override
    public void enableById(Integer id) {
        Music music = musicMapper.getById(id);
        music.setEnable(StateEnums.ENABLED.getCode());
        musicMapper.update(music);
    }

    @Override
    public void disable(Integer id) {
        Music music = musicMapper.getById(id);
        music.setEnable(StateEnums.NOT_ENABLE.getCode());
        musicMapper.update(music);
    }

    @Override
    public Page<Music> getByPage(Page<Music> page) {
        //查询数据
        List<Music> aboutList = musicMapper.getByPage(page);
        page.setList(aboutList);
        //查询总数
        int totalCount = musicMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
