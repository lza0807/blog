package com.lza.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回对象
 *
 * @author 杨德石
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1205790933778209322L;
    /**
     * 响应业务状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 是否加密。1加密0未加密。默认不加密
     */
    private Integer encrypt = 0;

    /**
     * 默认构造，默认为操作成功
     */
    public Result() {
        this.code = 20000;
        this.msg = "操作成功！";
    }

    /**
     * 全参数构造，自己设置code，msg，data
     *
     * @param code
     * @param msg
     * @param data
     */
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 无data构造，一般用于操作失败、删除更新等操作
     *
     * @param code
     * @param msg
     */
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 默认成功构造，自定义返回文本
     *
     * @param msg
     */
    public Result(String msg) {
        this.code = 20000;
        this.msg = msg;
    }

    /**
     * 自定义返回结果构造
     */
    public Result(Integer code) {
        this.code = code;
        this.msg = "操作失败！";
    }

    /**
     * 默认成功构造，自定义返回数据
     *
     * @param data
     */
    public Result(T data) {
        this.code = 20000;
        this.msg = "操作成功！";
        this.data = data;
    }

    /**
     * 默认成功构造，自定义文本和data
     *
     * @param msg
     * @param data
     */
    public Result(String msg, T data) {
        this.code = 20000;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", encrypt=" + encrypt +
                '}';
    }
}
