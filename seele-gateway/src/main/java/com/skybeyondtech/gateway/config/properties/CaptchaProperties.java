package com.skybeyondtech.gateway.config.properties;


import com.skybeyondtech.gateway.enums.CaptchaTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 */
@Getter
@Setter
@Configuration
@RefreshScope
@ConfigurationProperties("security.captcha")
public class CaptchaProperties {
    /**
     * 验证码开关
     */
    private Boolean enabled = false;

    /**
     * 验证码类型
     */
    private CaptchaTypeEnum type = CaptchaTypeEnum.CHAR;
}
