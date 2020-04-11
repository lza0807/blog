package com.lza.blog.pojo;

import lombok.Data;

/**
 * 友情链接
 * @Author: 刘子安
 * @Date: 2020/2/9 14:43
 * @Version 1.0
 */
@Data
public class Link {

    private Integer linkId;
    private String linkName;
    private String linkUrl;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer deleted;

}
