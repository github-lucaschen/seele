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
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "oum_department", schema = "seele")
@ToString
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Schema(description = "部门")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update oum_department set is_deleted = 1 where id = ?")
public class OumDepartmentPO extends PO {
    @Column(name = "parent_id")
    @Schema(description = "父部门id")
    private Long parentId;

    @Size(max = 50)
    @Column(name = "ancestors", length = 50)
    @Schema(description = "祖级列表")
    private String ancestors;

    @Size(max = 30)
    @Column(name = "department_name", length = 30)
    @Schema(description = "部门名称")
    private String departmentName;

    @Column(name = "order_number")
    @Schema(description = "显示顺序")
    private Integer orderNumber;

    @Size(max = 20)
    @Column(name = "leader", length = 20)
    @Schema(description = "负责人")
    private String leader;

    @Size(max = 11)
    @Column(name = "phone", length = 11)
    @Schema(description = "联系电话")
    private String phone;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    @Schema(description = "邮箱")
    private String email;

    @Column(name = "is_enabled", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "部门状态")
    private Integer enabled;

}