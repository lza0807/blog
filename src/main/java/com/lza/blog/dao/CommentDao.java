package com.lza.blog.dao;

import com.lza.blog.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 23:38 2020/4/10
 */
@Repository
public interface CommentDao extends MongoRepository<Comment,String> {

    /**
     * 根据评论数跟点赞量进行查询  查询 CommentUser 字段相同   CommentGood大于参数的值的集合
     * @return List<Comment>
     */
    List<Comment> findByCommentUserEqualsAndCommentGoodGreaterThanEqual(Integer userId, Integer goodsCount);


    List<Comment> findByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(String blogId);
}
