package com.lza.blog.service;


import com.lza.blog.pojo.Comment;
import com.lza.blog.pojo.CommentGoods;
import com.lza.blog.utils.Page;

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

    /**
     * 根据评论id和用户点赞
     * @param commentGoods
     */
    void goodByCommentIdAndUser(CommentGoods commentGoods);

    /**
     * 根据评论id查询点赞数
     * @param commentId
     * @return
     */
    int getGoodsCount(String commentId);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Comment> getByPage(Page<Comment> page);

    /**
     * 后台分页查询
     * @param page
     * @return
     */
    Page<Comment> getByPageBack(Page<Comment> page);
}
