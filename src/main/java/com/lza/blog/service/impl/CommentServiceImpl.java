package com.lza.blog.service.impl;


import com.lza.blog.dao.CommentDao;
import com.lza.blog.dao.CommentGoodsDao;
import com.lza.blog.mapper.BlogMapper;
import com.lza.blog.mapper.CommentMapper;
import com.lza.blog.mapper.UserMapper;
import com.lza.blog.pojo.Blog;
import com.lza.blog.pojo.Comment;
import com.lza.blog.pojo.CommentGoods;
import com.lza.blog.pojo.User;
import com.lza.blog.service.CommentService;
import com.lza.blog.utils.IdWorker;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.ShiroUtils;
import com.lza.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CommentGoodsDao commentGoodsDao;

    @Override
    public void save(Comment comment) {
        comment.setCommentGood(0);
        comment.setId(idWorker.nextId()+"");
        comment.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //保存用户信息
        User user = userMapper.getById(comment.getCommentUser());
        comment.setUser(user);
        //保存博客信息
        Blog blog = blogMapper.getBlogInfoById(comment.getCommentBlog());
        comment.setBlog(blog);
        commentDao.save(comment);
    }

    @Override
    public List<Comment> getByBlog(String blogId) {
        // 查询博客评论
        List<Comment> commentList = commentDao.findByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(blogId);
        // 查询点赞情况
        // 取出评论id
        List<String> commentIds = commentList.stream().map(Comment::getId).collect(Collectors.toList());
        List<CommentGoods> commentGoodsList = commentGoodsDao.findByCommentIdIn(commentIds);
        // 遍历去封装评论情况
        commentList.forEach(e -> {
            commentGoodsList.forEach(good -> {
                if (e.getId().equals(good.getCommentId())) {
                    // 匹配到了评论记录
                    e.setCommentFlag(true);
                }
            });
        });
        return commentList;
    }

    @Override
    public void deleteByid(String id) {
        commentDao.deleteById(id);
    }

    @Override
    public void goodByCommentIdAndUser(CommentGoods commentGoods) {
        User user = (User) ShiroUtils.getLoginUser();
        commentGoods.setUserId(user.getUserId());
        // 取出评论id，点赞数+1
        String commentId = commentGoods.getCommentId();
        Comment comment = commentDao.findById(commentId).get();
        comment.setCommentGood(comment.getCommentGood() + 1);
        commentDao.save(comment);
        try {
            commentGoods.setId(idWorker.nextId() + "");
            commentGoodsDao.save(commentGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getGoodsCount(String commentId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = commentGoodsDao.countByUserIdAndCommentId(user.getUserId(), commentId);
        return count;
    }

    @Override
    public Page<Comment> getByPage(Page<Comment> page) {
        User user = (User) ShiroUtils.getLoginUser();
        Comment comment = new Comment();
        comment.setCommentUser(user.getUserId());
        Example<Comment> example = Example.of(comment);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> p = commentDao.findAll(example, pageable);
        // 封装总页数、总条数、数据
        page.setTotalCount((int)p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }

    @Override
    public Page<Comment> getByPageBack(Page<Comment> page) {
        Comment comment = new Comment();
        String blogTitle = (String) page.getParams().get("blogTitle");
        if(StringUtils.isBlank(blogTitle)) {
            blogTitle = "";
        }
        String nickname = (String) page.getParams().get("nickname");
        if(StringUtils.isBlank(nickname)) {
            nickname = "";
        }
        Blog blog = new Blog();
        blog.setBlogTitle(blogTitle);
        comment.setBlog(blog);
        User user = new User();
        user.setNickname(nickname);
        comment.setUser(user);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> p = commentDao.getByBlogTitleAndNickname(comment, pageable);
        // 封装总页数、总条数、数据
        page.setTotalCount((int)p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }
}
