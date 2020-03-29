package com.lza.blog.mapper;


import com.lza.blog.pojo.Link;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 评论表Mapper
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 */
@Component
public interface LinkMapper {


    /**
     * 保存
     * @param link
     */
    void save(Link link);
}
