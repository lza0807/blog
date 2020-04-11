package com.lza.blog.service;


import com.lza.blog.pojo.Comment;

import java.util.List;

/**
 * <p>
 * 评论表服务层接口
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
public interface CommentService {

    /**
     * 保存帖子评论
     * @param comment
     */
    void save(Comment comment);

    /**
     * 根据博客id查询评论
     * @param blogId
     * @return
     */
    List<Comment> getByBlog(String blogId);

    /**
     * 根据id删除
     * @param id
     */
    void deleteByid(String id);
}
