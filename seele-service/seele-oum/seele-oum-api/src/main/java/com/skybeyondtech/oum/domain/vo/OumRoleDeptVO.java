package com.skybeyondtech.oum.domain.vo;

import com.skybeyondtech.common.jpa.domain.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OumRoleDeptVO extends VO {
    @Schema(description = "角色Id")
    private Long roleId;

    @Schema(description = "部门Id")
    private Long departmentId;
}