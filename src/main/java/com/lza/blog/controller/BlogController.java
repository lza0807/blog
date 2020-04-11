package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.pojo.Blog;
import com.lza.blog.pojo.BlogCollection;
import com.lza.blog.pojo.BlogGoods;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.StringUtils;
import com.lza.blog.vo.BlogVo;
import com.lza.blog.service.BlogService;
import com.lza.blog.utils.Result;
import com.lza.blog.vo.TimeLineVo;
import javafx.animation.Timeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 18:22 2020/3/30
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 添加博客
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Blog blog) {
        this.blogService.save(blog);
        return new Result<>("添加成功!");
    }

    /**
     * 根据id查询博客
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<Object> get(@PathVariable String id) {
        Blog blog = this.blogService.getBlogInfoById(id);
        return new Result<>(blog);

    }

    /**
     * 根据id更新博客
     *
     * @param blog
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result<Object> updateBlogInfoById(@RequestBody Blog blog) {
        this.blogService.updateBlogInfoById(blog);
        return new Result<>("更新成功!");
    }

    /**
     * 根据id阅读博客
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public Result<Object> readById(@PathVariable String id) {
        BlogVo blog = this.blogService.readById(id);
        return new Result<>(blog);
    }

    /**
     * 根据id删除博客
     *
     * @param id id
     * @return Result<Object>
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable String id) {
        this.blogService.delete(id);
        return new Result<>("删除成功!");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            //排序列不为空
            String[] sortColumns = {"blog_goods", "blog_read", "blog_collection", "type_name", "blog_comment", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法!");
            }
        }
        page = this.blogService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 推荐阅读
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/recomRead", method = RequestMethod.GET)
    public Result<List<BlogVo>> recomRead() {
        List<BlogVo> blogVoList = blogService.recomRead();
        return new Result<>(blogVoList);
    }

    /**
     * 时间轴
     */
    @RequestMapping(value = "/getTimeLine", method = RequestMethod.GET)
    public Result<List<TimeLineVo>> getTimeLine() {
        List<TimeLineVo> timeList = new ArrayList<>(16);
        List<BlogVo> blogVoList = blogService.getTimeLine();
        blogVoList.forEach(e -> {
            String blogMonth = e.getBlogMonth();
            TimeLineVo timeLineVo = new TimeLineVo();
            timeLineVo.setMonth(blogMonth);
            if(timeList.contains(timeLineVo)) {
                // 取出对应的数据
                TimeLineVo timeLine = getTimeLineForList(timeList, timeLineVo);
                List<BlogVo> list = timeLine.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLine.setList(list);
            } else {
                List<BlogVo> list = timeLineVo.getList();
                if(list == null) {
                    list = new ArrayList<>(8);
                }
                list.add(e);
                timeLineVo.setList(list);
                timeList.add(timeLineVo);
            }
        });
        return new Result<>(timeList);
    }



    /**
     * 点赞
     * @param blogGoods
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public Result<Object> good(@RequestBody BlogGoods blogGoods) {
        if(StringUtils.isBlank(blogGoods.getBlogId())) {
            return new Result<>("博客id不能为空！");
        }
        blogService.goodByBlogAndUser(blogGoods);
        return new Result<>("点赞成功！");
    }

    /**
     * 根据博客id和当前登录用户查询点赞记录
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getGood/{blogId}", method = RequestMethod.GET)
    public Result<Integer> getGood(@PathVariable String blogId) {
        int count = blogService.getGoodsCount(blogId);
        return new Result<>(count);
    }

    /**
     * 收藏
     * @param blogCollection
     * @return
     */
    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public Result<Object> collection(@RequestBody BlogCollection blogCollection) {
        if(StringUtils.isBlank(blogCollection.getBlogId())) {
            return new Result<>("博客id不能为空！");
        }
        blogService.collectionByBlogId(blogCollection);
        return new Result<>("收藏成功！");
    }

    /**
     * 根据博客id和当前登录用户查询收藏记录
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getCollection/{blogId}", method = RequestMethod.GET)
    public Result<Integer> getCollection(@PathVariable String blogId) {
        int count = blogService.getCollectionCount(blogId);
        return new Result<>(count);
    }

    /**
     * 分页查询我的收藏
     * @param page
     * @return
     */
    @RequestMapping(value = "/getCollectionList", method = RequestMethod.POST)
    public Result<Page<BlogCollection>> getCollectionList(@RequestBody Page<BlogCollection> page) {
        page = blogService.getCollectionByPage(page);
        return new Result<>(page);
    }

    /**
     * 获取对应的timeLine
     * @param timeList
     * @param timeLineVo
     * @return
     */
    private TimeLineVo getTimeLineForList(List<TimeLineVo> timeList, TimeLineVo timeLineVo) {
        for (TimeLineVo lineVo : timeList) {
            if(timeLineVo.getMonth().equals(lineVo.getMonth())) {
                return lineVo;
            }
        }
        return null;
    }

}
