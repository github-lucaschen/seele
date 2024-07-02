package com.skybeyondtech.base.domain.vo;


import com.skybeyondtech.common.jpa.domain.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OperationLogVO extends VO {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String userCode;

    /**
     * 客户端名称
     */
    @Schema(description = "客户端编码")
    private String clientCode;

    /**
     * 操作描述
     */
    @Schema(description = "操作描述")
    private String description;

    /**
     * 日志类型
     */
    @Schema(description = "日志类型")
    private Integer logType;

    /**
     * 操作类型
     */
    @Schema(description = "操作类型")
    private Integer operationType;

    /**
     * 类名
     */
    @Schema(description = "类名")
    private String className;

    /**
     * 方法名
     */
    @Schema(description = "方法名")
    private String method;

    /**
     * IP
     */
    @Schema(description = "IP")
    private String ip;

    /**
     * 请求地址
     */
    @Schema(description = "请求地址")
    private String url;

    /**
     * 请求类型
     */
    @Schema(description = "请求类型")
    private String httpMethod;

    /**
     * 浏览器信息
     */
    @Schema(description = "浏览器信息")
    private String userAgent;

    /**
     * 操作请求参数
     */
    @Schema(description = "操作请求参数")
    private String parameter;

    /**
     * 请求耗时
     */
    @Schema(description = "请求耗时")
    private Long requestTime;

    /**
     * 返回类型
     */
    @Schema(description = "返回类型")
    private Integer resultType;

    /**
     * 返回值
     */
    @Schema(description = "返回值")
    private String response;

    /**
     * 错误消息
     */
    @Schema(description = "错误消息")
    private String errorMessage;
}
