package com.lza.blog.vo;

import com.lza.blog.pojo.Blog;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 23:50 2020/3/30
 */
@Data
public class BlogVo extends Blog implements Serializable{
    private String typeName;
}
