package com.skybeyondtech.common.security.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO {
    /**
     * Token
     */
    private String accessToken;
    /**
     * 过期时间
     */
    private Long expiresIn;
}
