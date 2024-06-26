package com.skybeyondtech.common.core.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class R<T> {
    /**
     * 成功
     */
    public static final Boolean SUCCESS_DEFAULT_CODE = Boolean.TRUE;

    /**
     * 失败
     */
    public static final Boolean FAILURE_DEFAULT_CODE = Boolean.FALSE;

    /**
     * 请求数据
     */
    private T data;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回码
     */
    private Boolean succeed;

    public static <T> R<T> ok() {
        return result(null, SUCCESS_DEFAULT_CODE, null);
    }

    public static <T> R<T> ok(final T data) {
        return result(data, SUCCESS_DEFAULT_CODE, null);
    }

    public static <T> R<T> ok(final T data, final String message) {
        return result(data, SUCCESS_DEFAULT_CODE, message);
    }

    public static <T> R<T> fail() {
        return result(null, FAILURE_DEFAULT_CODE, null);
    }

    public static <T> R<T> fail(final String message) {
        return result(null, FAILURE_DEFAULT_CODE, message);
    }

    public static <T> R<T> fail(final T data) {
        return result(data, FAILURE_DEFAULT_CODE, null);
    }

    public static <T> R<T> fail(final T data, final String message) {
        return result(data, FAILURE_DEFAULT_CODE, message);
    }

    private static <T> R<T> result(final T data, final Boolean succeed, final String message) {
        final R<T> result = new R<>();
        result.setData(data);
        result.setMessage(message);
        result.setSucceed(succeed);
        return result;
    }
}
