package com.skybeyondtech.gateway.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCodeVO {
    /**
     * 是否开启验证码
     */
    private Boolean enabled;

    /**
     * 主键
     */
    private String uuid;

    /**
     * 图片 Base64格式
     */
    private String image;
}
