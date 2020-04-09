package com.lza.blog.service;


import com.lza.blog.pojo.Music;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;

import java.util.List;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
public interface MusicService {

    /**
     * 保存
     * @param music
     */
    void save(Music music);

    /**
     * 修改
     * @param music
     */
    void update(Music music);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 启用
     * @param id
     * @return
     */
    void enableById(Integer id);

    /**
     * 禁用
     * @param id
     * @return
     */
    void disable(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Music> getByPage(Page<Music> page);

    /**
     * 查询所有音乐
     * @return
     */
    List<Music> getList();
}
