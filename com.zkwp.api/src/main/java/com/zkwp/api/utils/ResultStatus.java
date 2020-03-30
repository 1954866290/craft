package com.zkwp.api.utils;

/**
 * @auther zhangkun
 * @date 2020/3/14 22:20
 **/
public enum ResultStatus {
    SUCCESS("200","操作成功"),
    FAILED("500","操作失败"),
    VALIDATE_FAILED("404", "参数检验失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限");

    private String status;
    private String message;
    private ResultStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }
    public String getstatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
