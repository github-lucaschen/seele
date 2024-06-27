package com.skybeyondtech.common.web.advice;

import com.skybeyondtech.common.core.domain.R;
import com.skybeyondtech.common.core.util.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.skybeyondtech")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(final MethodParameter returnType,
                            final Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(R.class);
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
                                  final MediaType selectedContentType,
                                  final Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  final ServerHttpRequest request,
                                  final ServerHttpResponse response) {
        if (returnType.getParameterType().isAssignableFrom(String.class)) {
            return ObjectUtils.defaultIfNull(JsonUtils.to(R.ok(body)), body.toString());
        }
        if (returnType.getParameterType().isAssignableFrom(R.class)) {
            return ObjectUtils.defaultIfNull(JsonUtils.to(body), body.toString());
        }
        return R.ok(body);
    }
}