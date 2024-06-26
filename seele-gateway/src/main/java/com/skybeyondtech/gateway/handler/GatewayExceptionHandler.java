package com.skybeyondtech.gateway.handler;

import com.skybeyondtech.common.core.domain.R;
import com.skybeyondtech.common.core.util.JsonUtils;
import com.skybeyondtech.common.core.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(final ServerWebExchange exchange, final Throwable ex) {
        final ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        final HttpStatus httpStatus;
        final String message;
        if (ex instanceof NotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
            message = "[GATEWAY] 404 SERVICE_NOT_FOUND";
        } else if (ex instanceof ResponseStatusException) {
            final ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            httpStatus = responseStatusException.getStatus();
            message = "[GATEWAY] " + responseStatusException.getMessage();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "[GATEWAY] 500 INTERNAL_SERVER_ERROR";
        }
        log.error("[GATEWAY] path: {}, message: {}",
                exchange.getRequest().getPath(), ex.getMessage());
        final String result = JsonUtils.to(R.fail(message));
        return ServletUtils.webFluxResponseWriter(response, httpStatus, result);
    }
}
