package com.lza.blog.service;


import com.lza.blog.pojo.Blog;
import com.lza.blog.utils.Page;
import com.lza.blog.vo.BlogVo;

import java.util.List;

/**
 * <p>
 * 博客表服务层接口
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
public interface BlogService {


    /**
     * 添加博客
     * @param blog
     * @return
     */
    void save(Blog blog);

    /**
     * 根据id查询博客
     * @param id
     * @return
     */

    Blog getBlogInfoById(String id);

    /**
     * 根据id更新博客
     * @param blog
     * @return
     */

    void updateBlogInfoById(Blog blog);

    /**
     * 根据id阅读博客
     * @param id
     * @return
     */
    BlogVo readById(String id);

    /**
     * 根据id删除博客
     * @param id id
     * @return  Result<Object>
     */
    void delete(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 推荐阅读
     * @return
     */
    List<BlogVo> recomRead();

    /**
     * 查询时间抽
     * @return
     */
    List<BlogVo> getTimeLine();
}
