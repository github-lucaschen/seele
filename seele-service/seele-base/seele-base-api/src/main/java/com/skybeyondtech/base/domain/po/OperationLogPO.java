package com.skybeyondtech.base.domain.po;


import com.skybeyondtech.common.jpa.domain.po.PO;
import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.base.enums.ResultTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "operation_log")
@Schema(description = "用户消息推送方式")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update operation_log set is_deleted = 1 where id = ?")
public class OperationLogPO extends PO {

    /**
     * 用户名
     */
    @Size(max = 70)
    @NotNull
    @Column(name = "user_code", nullable = false, length = 70)
    @Schema(description = "用户名")
    private String userCode;

    /**
     * 客户端名称
     */
    @Size(max = 20)
    @NotNull
    @Column(name = "client_code", nullable = false, length = 20)
    @Schema(description = "客户端编码")
    private String clientCode;

    /**
     * 操作描述
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "description", nullable = false, length = 100)
    @Schema(description = "操作描述")
    private String description;

    /**
     * 日志类型
     */
    @Column(name = "log_type", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "日志类型")
    private Integer logType;

    /**
     * 操作类型
     */
    @Column(name = "operation_type", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "操作类型")
    private Integer operationType;

    /**
     * 类名
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "class_name", nullable = false, length = 200)
    @Schema(description = "类名")
    private String className;

    /**
     * 方法名
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "method", nullable = false, length = 100)
    @Schema(description = "方法名")
    private String method;

    /**
     * IP
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "ip", nullable = false, length = 100)
    @Schema(description = "IP")
    private String ip;

    /**
     * 请求地址
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "url", nullable = false, length = 200)
    @Schema(description = "请求地址")
    private String url;

    /**
     * 请求类型
     */
    @Size(max = 10)
    @NotNull
    @Column(name = "http_method", nullable = false, length = 10)
    @Schema(description = "请求类型")
    private String httpMethod;

    /**
     * 浏览器信息
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "user_agent", nullable = false, length = 200)
    @Schema(description = "浏览器信息")
    private String userAgent;

    /**
     * 操作请求参数
     */
    @Size(max = 500)
    @NotNull
    @Column(name = "parameter", nullable = false, length = 500)
    @Schema(description = "操作请求参数")
    private String parameter;

    /**
     * 请求耗时
     */
    @Column(name = "request_time", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "请求耗时")
    private Long requestTime;

    /**
     * 返回类型
     */
    @Column(name = "result_type", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "返回类型")
    private Integer resultType;

    /**
     * 返回值
     */
    @Size(max = 1000)
    @Column(name = "response", length = 1000)
    @Schema(description = "返回值")
    private String response;

    /**
     * 错误消息
     */
    @Size(max = 1000)
    @Column(name = "error_message", length = 1000)
    @Schema(description = "错误消息")
    private String errorMessage;

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        final OperationLogPO that = (OperationLogPO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
