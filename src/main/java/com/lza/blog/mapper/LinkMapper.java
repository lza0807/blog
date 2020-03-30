package com.lza.blog.mapper;


import com.lza.blog.pojo.Link;
import org.springframework.stereotype.Component;

import java.util.List;


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

    /**
     * 修改友情链接
     * @param link
     * @return
     */
    void updateByID(Link link);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Link getLinkInfoById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Link> getAllLinkInfo();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    void delete(Integer id);
}
