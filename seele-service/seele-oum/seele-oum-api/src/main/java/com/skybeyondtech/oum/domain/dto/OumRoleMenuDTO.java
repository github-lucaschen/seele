package com.skybeyondtech.oum.domain.dto;

import com.skybeyondtech.common.jpa.domain.dto.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OumRoleMenuDTO extends DTO {
    @NotNull
    @Schema(description = "角色id")
    private Long roleId;

    @NotNull
    @Schema(description = "菜单id")
    private Long menuId;

}