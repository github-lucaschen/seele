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
public class OumPostDTO extends DTO {
    @Size(max = 64)
    @NotNull
    @Schema(description = "岗位编码")
    private String postCode;

    @Size(max = 50)
    @NotNull
    @Schema(description = "岗位名称")
    private String postName;

    @Schema(description = "显示顺序")
    private Long orderNumber;

    @Schema(description = "状态")
    private Integer enabled;
}