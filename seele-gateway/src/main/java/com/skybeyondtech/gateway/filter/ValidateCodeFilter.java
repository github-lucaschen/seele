package com.skybeyondtech.gateway.filter;

import com.skybeyondtech.common.core.domain.R;
import com.skybeyondtech.common.core.util.JsonUtils;
import com.skybeyondtech.common.core.util.ServletUtils;
import com.skybeyondtech.gateway.config.properties.CaptchaProperties;
import com.skybeyondtech.gateway.domain.dto.ValidateCodeDTO;
import com.skybeyondtech.gateway.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

    private static final String[] VALIDATE_URL = new String[]{"/auth/login", "/auth/register"};

    private final ValidateCodeService validateCodeService;

    private final CaptchaProperties captchaProperties;

    @Override
    public GatewayFilter apply(final Object config) {
        return (exchange, chain) -> {
            final ServerHttpRequest request = exchange.getRequest();

            // 非登录/注册请求或验证码关闭，不处理
            if (!StringUtils.equalsAnyIgnoreCase(request.getURI().getPath(), VALIDATE_URL)
                    || BooleanUtils.isFalse(captchaProperties.getEnabled())) {
                return chain.filter(exchange);
            }
            try {
                final String rspStr = resolveBodyFromRequest(request);
                final ValidateCodeDTO validateCodeDTO = JsonUtils.to(rspStr, ValidateCodeDTO.class);
                validateCodeService.checkCaptcha(validateCodeDTO);
            } catch (Exception e) {
                return ServletUtils.webFluxResponseWriter(exchange.getResponse(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        R.fail(e.getMessage()));
            }
            return chain.filter(exchange);
        };
    }

    private String resolveBodyFromRequest(final ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        final Flux<DataBuffer> body = serverHttpRequest.getBody();
        final AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            final CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
