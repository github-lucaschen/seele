package com.skybeyondtech.gateway.filter;

import com.skybeyondtech.common.core.constant.CacheConstants;
import com.skybeyondtech.common.core.constant.SecurityConstants;
import com.skybeyondtech.common.core.constant.TokenConstants;
import com.skybeyondtech.common.core.util.JwtUtils;
import com.skybeyondtech.common.core.util.ServletUtils;
import com.skybeyondtech.common.redis.service.RedisService;
import com.skybeyondtech.gateway.config.properties.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {

    private final IgnoreWhiteProperties ignoreWhiteProperties;

    private final RedisService<String> redisService;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpRequest.Builder mutate = request.mutate();

        final String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (CollectionUtils.containsAny(ignoreWhiteProperties.getWhites(),
                Collections.singletonList(url))) {
            return chain.filter(exchange);
        }
        final String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return unauthorizedResponse(exchange, "令牌不能为空");
        }
        final Claims claims = JwtUtils.parseToken(token);
        if (claims == null) {
            return unauthorizedResponse(exchange, "令牌已过期或验证不正确！");
        }
        final String userKey = JwtUtils.getUserKey(claims);
        final Boolean isLogin = redisService.hasKey(getTokenKey(userKey));
        if (BooleanUtils.isFalse(isLogin)) {
            return unauthorizedResponse(exchange, "登录状态已过期");
        }
        final String userId = JwtUtils.getUserId(claims);
        final String userCode = JwtUtils.getUserCode(claims);
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userCode)) {
            return unauthorizedResponse(exchange, "令牌验证失败");
        }

        // 设置用户信息到请求
        addHeader(mutate, SecurityConstants.USER_KEY, userKey);
        addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userId);
        addHeader(mutate, SecurityConstants.DETAILS_USER_CODE, userCode);
        // 内部请求来源参数清除
        removeHeader(mutate, SecurityConstants.FROM_SOURCE);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private void addHeader(final ServerHttpRequest.Builder mutate,
                           final String name,
                           final Object value) {
        if (Objects.isNull(value)) {
            return;
        }
        final String valueStr = value.toString();
        final String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(final ServerHttpRequest.Builder mutate, final String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String message) {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return ServletUtils
                .webFluxResponseWriter(exchange.getResponse(), HttpStatus.UNAUTHORIZED, message);
    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(final String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(final ServerHttpRequest request) {
        final String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            return token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
