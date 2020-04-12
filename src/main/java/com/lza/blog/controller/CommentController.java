package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.mapper.CommentMapper;
import com.lza.blog.pojo.Comment;
import com.lza.blog.pojo.CommentGoods;
import com.lza.blog.pojo.User;
import com.lza.blog.service.CommentService;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.ShiroUtils;
import com.lza.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 17:31 2020/4/11
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Comment comment) {
        //获取当前登录的userID
        User user = (User) ShiroUtils.getLoginUser();
        comment.setCommentUser(user.getUserId());
        if (StringUtils.isBlank(comment.getCommentBlog())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "博客id不能为空!");
        }
        commentService.save(comment);
        return new Result<>("评论成功!");
    }

    /**
     * 查询评论
     *
     * @param blogId
     * @return
     */
    @RequestMapping(value = "getByBlog/{blogId}", method = RequestMethod.GET)
    public Result<Object> getByBlog(@PathVariable String blogId) {
        if (StringUtils.isBlank(blogId)) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "博客id不能为空!");
        }
        List<Comment> list = commentService.getByBlog(blogId);
        return new Result<>(list);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.DELETE)
    public Result<Object> deleteById(@PathVariable  String id){
        commentService.deleteByid(id);
        return new Result<>("删除成功!");
    }

    /**
     * 点赞
     *
     * @param commentGoods
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public Result<Object> good(@RequestBody CommentGoods commentGoods) {
        if (StringUtils.isBlank(commentGoods.getCommentId())) {
            return new Result<>("评论id不能为空！");
        }
        commentService.goodByCommentIdAndUser(commentGoods);
        return new Result<>("点赞成功！");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Result<Page<Comment>> getList(@RequestBody Page<Comment> page) {
        page = commentService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 后台分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<Comment>> getByPage(@RequestBody Page<Comment> page) {
        page = commentService.getByPageBack(page);
        return new Result<>(page);
    }


}
