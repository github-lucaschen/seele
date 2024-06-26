package com.skybeyondtech.gateway.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.google.code.kaptcha.Constants.KAPTCHA_OBSCURIFICATOR_IMPL;
import static com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_IMPL;

/**
 * 验证码配置
 */
@Configuration
public class CaptchaConfig {

    @Bean
    public DefaultKaptcha captchaProducer() {
        final DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        final Properties properties = new Properties();
        // 图片样式
        // 水纹com.google.code.kaptcha.impl.WaterRipple
        // 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
        // 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }

    @Bean
    public DefaultKaptcha captchaProducerMath() {
        final DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        final Properties properties = new Properties();
        properties.setProperty(KAPTCHA_TEXTPRODUCER_IMPL, "com.skybeyondtech.gateway.config.KaptchaMathCreator");
        // 图片样式
        // 水纹com.google.code.kaptcha.impl.WaterRipple
        // 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
        // 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
