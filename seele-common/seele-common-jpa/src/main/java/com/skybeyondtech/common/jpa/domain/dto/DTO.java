package com.skybeyondtech.common.jpa.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class DTO {

    /**
     * 主键
     */
    @Schema(description = "主键，自增 ID")
    private Long id;

    /**
     * 页码
     */
    @Builder.Default
    @Schema(description = "页码")
    private Integer page = 0;

    /**
     * 页大小
     */
    @Builder.Default
    @Schema(description = "页大小")
    private Integer size = 10;
}