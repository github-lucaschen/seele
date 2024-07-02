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

@Getter
@Setter
@Entity
@Table(name = "oum_role_dept", schema = "seele")
@ToString
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Schema(description = "角色部门")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update oum_role_dept set is_deleted = 1 where id = ?")
public class OumRoleDeptPO extends PO {
    @NotNull
    @Column(name = "role_id", nullable = false)
    @Schema(description = "角色Id")
    private Long roleId;

    @NotNull
    @Column(name = "department_id", nullable = false)
    @Schema(description = "部门Id")
    private Long departmentId;
}