package com.skybeyondtech.oum.domain.vo;

import com.skybeyondtech.common.jpa.domain.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OumUserRoleVO extends VO {
    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "角色id")
    private Long roleId;

}