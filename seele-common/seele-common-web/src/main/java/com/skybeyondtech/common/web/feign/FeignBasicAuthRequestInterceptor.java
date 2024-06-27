package com.skybeyondtech.common.web.feign;

import com.skybeyondtech.common.core.context.HeaderCode;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(final RequestTemplate requestTemplate) {
        final ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(attributes)) {
            // 设置 Feign 请求标识
            requestTemplate.header(HeaderCode.FEIGN, BooleanUtils.TRUE);
            final String token = attributes.getRequest().getHeader(HeaderCode.TOKEN);
            if (StringUtils.isNoneBlank(token)) {
                requestTemplate.header(HeaderCode.TOKEN, token);
            }
            final String sourceType = attributes.getRequest().getHeader(HeaderCode.SOURCE_TYPE);
            if (StringUtils.isNoneBlank(sourceType)) {
                requestTemplate.header(HeaderCode.SOURCE_TYPE, sourceType);
            }
            final String userAgent = attributes.getRequest().getHeader(HeaderCode.USER_AGENT);
            if (StringUtils.isNoneBlank(userAgent)) {
                requestTemplate.header(HeaderCode.USER_AGENT, userAgent);
            }
        }
    }
}