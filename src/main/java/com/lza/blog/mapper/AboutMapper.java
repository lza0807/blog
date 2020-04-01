package com.lza.blog.mapper;


import com.lza.blog.pojo.About;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 */
@Component
public interface AboutMapper {

    /**
     * 保存
     * @param about
     */
    void save(About about);

    /**
     * 修改
     * @param about
     */
    void update(About about);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    About get(Integer id);

    /**
     * 阅读关于我
     * @return
     */
    About read();


    /**
     * 根据id删除
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 修改为启用状态
     * @param about
     */
    void enableState(About about);

    /**
     * 查询所有数据
     * @param page
     * @return
     */
    List<About> getByPage(Page<About> page);

    /**
     * 总条数
     * @param page
     * @return
     */
    int getCountByPage(Page<About> page);
}
