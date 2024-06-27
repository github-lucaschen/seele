package com.skybeyondtech.base.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CacheDTO {

    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private String value;
}
