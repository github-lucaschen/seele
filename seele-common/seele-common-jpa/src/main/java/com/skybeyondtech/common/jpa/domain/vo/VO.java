package com.skybeyondtech.common.jpa.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class VO {
    @Schema(description = "主键，自增 ID")
    private Long id;

    @Schema(description = "是否删除，1 是 0 否")
    private Short deleted;

    @Schema(description = "数据库保存时间")
    private LocalDateTime createTime;

    @Schema(description = "数据库更新时间")
    private LocalDateTime updateTime;
}