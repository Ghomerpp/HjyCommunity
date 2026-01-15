package com.msb.hjycommunity.common.core.exception;

/**
 * 基础异常类(（自定义异常类）)
 * 当代码运行中出现错误（比如 “用户不存在”“权限不足”），我们不用 Java 自带的普通异常（太简单，信息少），而是用这个自定义的BaseException，它能携带更详细的错误信息。
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/18 - 11 - 18 - 9:05
 * Description: com.msb.hjycommunity.common.core.exception
 */
public class BaseException extends RuntimeException{

    /*错误码*/
    private String code;

    /*错误消息*/
    private String defaultMessage;

    public BaseException() {
    }

    public BaseException(String code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
