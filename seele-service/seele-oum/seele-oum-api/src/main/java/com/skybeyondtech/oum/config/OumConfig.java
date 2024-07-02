package com.skybeyondtech.oum.config;

import com.skybeyondtech.oum.constant.OumConstant;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("all")
@EnableFeignClients(OumConstant.BASE_PACKAGE)
@ComponentScan(OumConstant.BASE_PACKAGE)
public class OumConfig {
}
