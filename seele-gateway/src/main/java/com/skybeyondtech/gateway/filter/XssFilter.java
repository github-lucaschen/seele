package com.skybeyondtech.gateway.filter;

import com.skybeyondtech.gateway.config.properties.XssProperties;
import io.netty.buffer.ByteBufAllocator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.HtmlUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Component
@ConditionalOnProperty(value = "security.xss.enabled", havingValue = "true")
@RequiredArgsConstructor
public class XssFilter implements GlobalFilter, Ordered {

    private final XssProperties xssProperties;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        // XSS 配置中未开启，则不过滤
        if (BooleanUtils.isFalse(xssProperties.getEnabled())) {
            return chain.filter(exchange);
        }
        final HttpMethod method = request.getMethod();

        // GET DELETE OPTIONS 不过滤
        if (method == null
                || method == HttpMethod.GET
                || method == HttpMethod.DELETE
                || method == HttpMethod.OPTIONS) {
            return chain.filter(exchange);
        }

        // 非 JSON 类型请求不过滤
        if (!isJsonRequest(exchange)) {
            return chain.filter(exchange);
        }

        final String url = request.getURI().getPath();

        // 排除名单中的不过滤
        if (CollectionUtils.containsAny(xssProperties.getExcludeUrls(), Collections.singleton(url))) {
            return chain.filter(exchange);
        }
        final ServerHttpRequestDecorator serverHttpRequestDecorator = requestDecorator(exchange);
        return chain.filter(exchange.mutate().request(serverHttpRequestDecorator).build());
    }

    private ServerHttpRequestDecorator requestDecorator(final ServerWebExchange exchange) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
                final Flux<DataBuffer> body = super.getBody();

                return body.buffer().map(dataBuffers -> {
                    final DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                    final DataBuffer join = dataBufferFactory.join(dataBuffers);
                    byte[] content = new byte[join.readableByteCount()];
                    join.read(content);
                    DataBufferUtils.release(join);
                    final String bodyStr = new String(content, StandardCharsets.UTF_8);
                    // 防 XSS 攻击过滤
                    final String cleanBodyStr = HtmlUtils.htmlEscape(bodyStr);
                    // 转成字节
                    byte[] bytes = cleanBodyStr.getBytes(StandardCharsets.UTF_8);
                    final NettyDataBufferFactory nettyDataBufferFactory =
                            new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                    final DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
                    buffer.write(bytes);
                    return buffer;
                });
            }

            @Override
            public HttpHeaders getHeaders() {
                final HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(super.getHeaders());
                // 由于修改了请求体的body，导致content-length长度不确定，因此需要删除原先的content-length
                httpHeaders.remove(HttpHeaders.CONTENT_LENGTH);
                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                return httpHeaders;
            }
        };
    }

    private boolean isJsonRequest(final ServerWebExchange exchange) {
        final String contentType =
                exchange.getRequest().getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        return StringUtils.startsWithIgnoreCase(contentType, MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
