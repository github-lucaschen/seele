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
public class OumMenuDTO extends DTO {
    @Size(max = 50)
    @NotNull
    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "显示顺序")
    private Long orderNumber;

    @Size(max = 200)
    @Schema(description = "路由地址")
    private String path;

    @Size(max = 255)
    @Schema(description = "组件路径")
    private String component;

    @Size(max = 255)
    @Schema(description = "路由参数")
    private String query;

    @Schema(description = "是否为外链")
    private Integer frame;

    @Schema(description = "是否缓存")
    private Integer cache;

    @Schema(description = "菜单类型")
    private Character menuType;

    @Schema(description = "菜单状态")
    private Integer visible;

    @Size(max = 100)
    @Schema(description = "权限标识")
    private String perms;

    @Size(max = 100)
    @Schema(description = "菜单图标")
    private String icon;

    @Size(max = 500)
    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态")
    private Integer enabled;
}