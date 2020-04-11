package com.lza.blog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lza.blog.dao.CommentDao;
import com.lza.blog.pojo.Blog;
import com.lza.blog.pojo.Comment;
import com.lza.blog.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private CommentDao commentDao;
    @Test
    public void testSave() {
        Comment comment = new Comment();
        comment.setCommentBlog("4");
        comment.setCommentContent("我是帖子的评论内容4 ");
        comment.setCommentGood(0);
        comment.setCommentUser(123);
        comment.setCreatedTime(LocalDate.now().toString());
        //保存评价人信息
        User user = new User();
        user.setUserId(123);
        user.setName("liuzian");
        //保存博客信息
        Blog blog = new Blog();
        blog.setBlogId("4");
        blog.setBlogContent("这是博客内容");
        comment.setBlog(blog);
        comment.setUser(user);
        commentDao.save(comment);
    }

    @Test
    public void findAll() {
        Optional<Comment> Comment = commentDao.findById("5e9196908e705d0c5cf06ac7");
        System.out.println(JSONObject.toJSONString(Comment));
    }





}
