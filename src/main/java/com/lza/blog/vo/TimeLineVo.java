package com.lza.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 22:22 2020/4/9
 */
@Data
public class TimeLineVo implements Serializable {
    private String month;
    private List<BlogVo> list;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        TimeLineVo that = (TimeLineVo) o;
        return month.equals(that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month);
    }
}
