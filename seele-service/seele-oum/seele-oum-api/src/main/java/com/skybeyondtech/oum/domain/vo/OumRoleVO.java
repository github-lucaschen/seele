package com.skybeyondtech.oum.domain.vo;

import com.skybeyondtech.common.jpa.domain.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OumRoleVO extends VO {
    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色权限字符串")
    private String roleKey;

    @Schema(description = "显示顺序")
    private Long orderNumber;

    @Schema(description = "数据范围")
    private Integer dataScope;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "菜单树选择项是否关联显示")
    private Integer menuStrictly;

    @Schema(description = "部门树选择项是否关联显示")
    private Integer departmentStrictly;

    @Schema(description = "角色状态")
    private Integer enabled;

}