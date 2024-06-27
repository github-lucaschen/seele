package com.skybeyondtech.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogTypeEnum {

    /**
     * 权限相关
     */
    AUTHENTICATE(1, "authenticate"),
    /**
     * 操作相关
     */
    OPERATION(2, "operation"),
    ;

    private final int code;
    private final String message;
}
