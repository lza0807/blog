package com.lza.blog.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 收藏时间实体类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Data
public class BlogCollection implements Serializable {

    private static final long serialVersionUID = -535915810554536111L;

    /**
     * 收藏id
     */
    private Integer collectionId;

    /**
     * 帖子id
     */
    private String blogId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收藏时间
     */
    private String collectionTime;

}
