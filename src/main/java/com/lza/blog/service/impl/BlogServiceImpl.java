package com.lza.blog.service.impl;


import com.lza.blog.mapper.BlogMapper;
import com.lza.blog.mapper.TypeMapper;
import com.lza.blog.pojo.Blog;
import com.lza.blog.utils.IdWorker;
import com.lza.blog.utils.Page;
import com.lza.blog.vo.BlogVo;
import com.lza.blog.pojo.Type;
import com.lza.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
}
