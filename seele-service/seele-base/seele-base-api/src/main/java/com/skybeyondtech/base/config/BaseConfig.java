package com.skybeyondtech.base.config;

import com.skybeyondtech.base.constant.BaseConstant;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("all")
@EnableFeignClients(BaseConstant.BASE_PACKAGE)
@ComponentScan(BaseConstant.BASE_PACKAGE)
public class BaseConfig {
}