package com.skybeyondtech.oum.domain.dto;

import com.skybeyondtech.common.jpa.domain.dto.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OumRoleDTO extends DTO {
    @Size(max = 30)
    @NotNull
    @Schema(description = "角色名称")
    private String roleName;

    @Size(max = 100)
    @NotNull
    @Schema(description = "角色权限字符串")
    private String roleKey;

    @Schema(description = "显示顺序")
    private Long orderNumber;

    @Schema(description = "数据范围")
    private Integer dataScope;

    @Size(max = 500)
    @Schema(description = "备注")
    private String remark;

    @Schema(description = "菜单树选择项是否关联显示")
    private Integer menuStrictly;

    @Schema(description = "部门树选择项是否关联显示")
    private Integer departmentStrictly;

    @Schema(description = "角色状态")
    private Integer enabled;

}