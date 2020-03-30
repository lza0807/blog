package com.lza.blog.mapper;
import com.lza.blog.pojo.Blog;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 博客表Mapper
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Component
public interface BlogMapper {

    /**
     * 添加博客
     * @param blog
     * @return
     */
    void save(Blog blog);
}
