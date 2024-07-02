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
@Entity
@Table(name = "oum_role", schema = "seele")
@ToString
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Schema(description = "角色")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update oum_role set is_deleted = 1 where id = ?")
public class OumRolePO extends PO {
    @Size(max = 30)
    @NotNull
    @Column(name = "role_name", nullable = false, length = 30)
    @Schema(description = "角色名称")
    private String roleName;

    @Size(max = 100)
    @NotNull
    @Column(name = "role_key", nullable = false, length = 100)
    @Schema(description = "角色权限字符串")
    private String roleKey;

    @Column(name = "order_number", columnDefinition = "int UNSIGNED not null")
    @Schema(description = "显示顺序")
    private Long orderNumber;

    @Column(name = "data_scope", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "数据范围")
    private Integer dataScope;

    @Size(max = 500)
    @Column(name = "remark", length = 500)
    @Schema(description = "备注")
    private String remark;

    @Column(name = "is_menu_strictly", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "菜单树选择项是否关联显示")
    private Integer menuStrictly;

    @Column(name = "is_department_strictly", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "部门树选择项是否关联显示")
    private Integer departmentStrictly;

    @Column(name = "is_enabled", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "角色状态")
    private Integer enabled;

}