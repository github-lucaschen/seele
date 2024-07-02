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
public class OumRoleDeptDTO extends DTO {
    @NotNull
    @Schema(description = "角色Id")
    private Long roleId;

    @NotNull
    @Schema(description = "部门Id")
    private Long departmentId;
}