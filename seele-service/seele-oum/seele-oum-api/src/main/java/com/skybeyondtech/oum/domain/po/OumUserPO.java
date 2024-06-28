package com.skybeyondtech.oum.domain.po;

import com.skybeyondtech.common.jpa.domain.po.PO;
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@SQLDelete(sql = "update oum_user set is_deleted = 1 where id = ?")
public class OumUserPO extends PO {
    /**
     * 部门 Id
     */
    @NotNull
    @Column(name = "dept_id", nullable = false)
    @Schema(description = "用户名")
    private Long deptId;
}
