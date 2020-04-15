package com.lza.blog.enums;
import lombok.Getter;
/**
 * @Description: 返回结果枚举
 * @Author: liuzian
 * @Date: Create in 23:02 2020/3/26
 */
@Getter
public enum ResultEnum {
    /**
     * 返回接口枚举,每个枚举代表着一个返回状态
     */
    SUCCESS(20000, "操作成功！"),
    ERROR(40000, "操作失败！"),
    DATA_NOT_FOUND(40001, "查询失败！"),
    PARAMS_NULL(40002, "参数不能为空！"),
    PARAMS_ERROR(40005, "参数不合法！"),

    NOT_LOGIN(40003, "当前账号未登录！")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
