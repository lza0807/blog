package com.lza.blog.mapper;

import com.lza.blog.pojo.Music;
import com.lza.blog.utils.Page;
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
public interface MusicMapper {


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
     */
    void delete(Integer id);

    /**
     * 分页
     * @param page
     * @return
     */
    List<Music> getByPage(Page<Music> page);

    /**
     * 总数
     * @param page
     * @return
     */
    int getCountByPage(Page<Music> page);
    /**
     * 查询所有音乐
     * @return
     */
    List<Music> getList();
}
