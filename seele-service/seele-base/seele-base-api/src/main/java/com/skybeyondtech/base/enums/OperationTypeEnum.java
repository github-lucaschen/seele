package com.skybeyondtech.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationTypeEnum {
    /**
     * 未知
     */
    UNKNOWN(0, "unknown"),
    /**
     * 新增
     */
    INSERT(10, "insert"),
    /**
     * 更新
     */
    UPDATE(20, "update"),
    /**
     * 删除
     */
    DELETE(40, "delete"),
    /**
     * 授权
     */
    GRANT(50, "grant"),
    /**
     * 导出
     */
    EXPORT(60, "export"),
    /**
     * 导入
     */
    IMPORT(70, "import"),
    /**
     * 清空
     */
    CLEAN(80, "clean"),
    /**
     * 登录
     */
    LOGIN(1000, "login"),
    /**
     * 退出
     */
    LOGOUT(1001, "logout"),
    /**
     * 强制退出
     */
    FORCE(1003, "force"),
    ;

    private final int code;
    private final String message;
}
