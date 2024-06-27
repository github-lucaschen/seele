package com.skybeyondtech.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultTypeEnum {

    SUCCESS("成功"),
    FAIL("失败"),
    ;

    private final String message;
}
