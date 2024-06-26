package com.skybeyondtech.gateway.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * XSS跨站脚本配置
 */
@Getter
@Setter
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.xss")
public class XssProperties {

    /**
     * Xss开关
     */
    private Boolean enabled = true;

    /**
     * 排除路径
     */
    private List<String> excludeUrls = new ArrayList<>();
}
