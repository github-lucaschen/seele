package com.skybeyondtech.common.log.annotation;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Log {

    /**
     * 操作描述
     *
     * @return {String}
     */
    String description() default "";

    /**
     * 日志类型
     *
     * @return {int}
     */
    LogTypeEnum logType() default LogTypeEnum.OPERATION;

    /**
     * 操作类型
     *
     * @return {int}
     */
    OperationTypeEnum operationType() default OperationTypeEnum.UNKNOWN;

    /**
     * 是否记录请求参数
     *
     * @return {boolean}
     */
    boolean request() default true;

    /**
     * 是否记录响应参数
     *
     * @return {boolean}
     */
    boolean response() default true;

    /**
     * 排除指定的参数
     *
     * @return String[]
     */
    String[] excludeParamNames() default {};
}