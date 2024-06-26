package com.skybeyondtech.gateway.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCodeDTO {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 值
     */
    private String code;
}
