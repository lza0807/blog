package com.lza.blog.service.impl;


import com.lza.blog.dao.BlogGoodsDao;
import com.lza.blog.dao.CollectionDao;
import com.lza.blog.mapper.BlogMapper;
import com.lza.blog.mapper.TypeMapper;
import com.lza.blog.pojo.*;
import com.lza.blog.utils.IdWorker;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.ShiroUtils;
import com.lza.blog.vo.BlogVo;
import com.lza.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客表服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BlogGoodsDao blogGoodsDao;

    @Autowired
    private CollectionDao collectionDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Blog blog) {
        blog.setBlogId(idWorker.nextId() + "");
        this.blogMapper.save(blog);

        //取出博客对应分类id
        Integer blogType = blog.getBlogType();
        Type type = typeMapper.getTypeInfoById(blogType);
        type.setTypeBlogCount(type.getTypeBlogCount() + 1);
        typeMapper.updateTypeById(type);
    }

    @Override
    public Blog getBlogInfoById(String id) {
        return this.blogMapper.getBlogInfoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBlogInfoById(Blog blog) {
        //更新博客的时候,分类修改了的话,旧的博客数 -1,新的 +1
        Blog oldBlog = blogMapper.getBlogInfoById(blog.getBlogId());
        blogMapper.updateBlogInfoByid(blog);
        Integer oldTypeId = oldBlog.getBlogType();
        Integer newTypeId = blog.getBlogType();
        if (!oldTypeId.equals(newTypeId)) {
            //证明分类是被修改了的,旧的那个博客数 -1
            Type oldType = typeMapper.getTypeInfoById(oldTypeId);
            oldType.setTypeBlogCount(oldType.getTypeBlogCount() - 1);
            typeMapper.updateTypeById(oldType);
            //新的+1
            Type newType = typeMapper.getTypeInfoById(newTypeId);
            newType.setTypeBlogCount(newType.getTypeBlogCount() + 1);
            typeMapper.updateTypeById(newType);
        }


    }

    /**
     * 添加阅读数
     *
     * @param id id
     * @return sa
     */
    @Override
    @Transactional(rollbackFor = Exception.class)//所有异常都回滚
    public BlogVo readById(String id) {
        //没点击一次阅读，阅读数都加一
        Blog blog = this.blogMapper.getBlogInfoById(id);
        blog.setBlogRead(blog.getBlogRead() + 1);
        blogMapper.updateBlogInfoByid(blog);
        //将blog转blogVo
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        //根据typeid查询对应的分类信息
        Type type = this.typeMapper.getTypeInfoById(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }

    /**
     * 根据id删除博客
     *
     * @param id id
     * @return Result<Object>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        this.blogMapper.delete(id);
    }

    @Override
    public Page<BlogVo> getByPage(Page<BlogVo> page) {
        //查询数据
        List<BlogVo> blogVoList = blogMapper.getByPage(page);
        page.setList(blogVoList);
        //查询总数
        int totalCount = blogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<BlogVo> recomRead() {

        return blogMapper.recomRead();
    }

    @Override
    public List<BlogVo> getTimeLine() {
        return blogMapper.getTimeLine();
    }

    @Override
    public void goodByBlogAndUser(BlogGoods blogGoods) {
        User user = (User) ShiroUtils.getLoginUser();
        blogGoods.setUserId(user.getUserId());
        // 取出博客id，点赞数+1
        String blogId = blogGoods.getBlogId();
        blogMapper.updateGoods(blogId);
        try {
            blogGoods.setId(idWorker.nextId() + "");
            blogGoodsDao.save(blogGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getGoodsCount(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = blogGoodsDao.countByUserIdAndBlogId(user.getUserId(), blogId);
        return count;
    }

    @Override
    public void collectionByBlogId(BlogCollection blogCollection) {
        User user = (User) ShiroUtils.getLoginUser();
        blogCollection.setUserId(user.getUserId());
        blogCollection.setUser(user);
        // 查询博客
        Blog blog = blogMapper.getBlogInfoById(blogCollection.getBlogId());
        blog.setBlogContent(null);
        blogCollection.setBlog(blog);

        blog.setBlogCollection(blog.getBlogCollection() + 1);
        blogMapper.updateBlogInfoByid(blog);
        try {
            blogCollection.setCollectionId(idWorker.nextId() + "");
            collectionDao.save(blogCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCollectionCount(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = collectionDao.countByBlogIdAndUserId(blogId, user.getUserId());
        return count;
    }

    @Override
    public Page<BlogCollection> getCollectionByPage(Page<BlogCollection> page) {
        User user = (User) ShiroUtils.getLoginUser();
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setUserId(user.getUserId());
        Example example = Example.of(blogCollection);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page p = collectionDao.findAll(example, pageable);
        page.setTotalCount((int) p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }
}
