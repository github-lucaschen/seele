package com.skybeyondtech.common.core.util;

import com.skybeyondtech.common.core.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servlet 工具类
 */
@Slf4j
public final class ServletUtils {

    private ServletUtils() {
    }

    /**
     * 获取 String 参数
     */
    public static String getParameter(final String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取 String 参数
     */
    public static String getParameter(final String name, final String defaultValue) {
        final String parameter = getParameter(name);
        return StringUtils.isBlank(parameter) ? defaultValue : parameter;
    }

    /**
     * 获取 Integer 参数
     */
    public static Integer getParameterToInt(final String name) {
        return NumberUtils.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取 Integer 参数
     */
    public static Integer getParameterToInt(final String name, final Integer defaultValue) {
        return NumberUtils.toInt(name, defaultValue);
    }

    /**
     * 获取 Boolean 参数
     */
    public static Boolean getParameterToBool(final String name) {
        return BooleanUtils.toBoolean(getRequest().getParameter(name));
    }

    /**
     * 获取 Boolean 参数
     */
    public static Boolean getParameterToBool(final String name, final Boolean defaultValue) {
        final String parameter = getRequest().getParameter(name);
        return StringUtils.isBlank(parameter) ? defaultValue : getParameterToBool(parameter);
    }

    /**
     * 获得所有请求参数
     *
     * @param request 请求对象{@link ServletRequest}
     * @return Map
     */
    public static Map<String, String[]> getParams(final ServletRequest request) {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }

    /**
     * 获得所有请求参数
     *
     * @param request 请求对象{@link ServletRequest}
     * @return Map
     */
    public static Map<String, String> getParamMap(final ServletRequest request) {
        return getParams(request).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> StringUtils.join(entry.getValue(), ","),
                        (o, n) -> n));
    }

    /**
     * 获取 Request
     */
    public static HttpServletRequest getRequest() {
        try {
            return getRequestAttributes().getRequest();
        } catch (Exception e) {
            log.error("Servlet::getRequest", e);
            return null;
        }
    }

    /**
     * 获取 Response
     */
    public static HttpServletResponse getResponse() {
        try {
            return getRequestAttributes().getResponse();
        } catch (Exception e) {
            log.error("Servlet::getResponse", e);
            return null;
        }
    }

    /**
     * 获取 Session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        } catch (Exception e) {
            log.error("Servlet::getRequestAttributes", e);
            return null;
        }
    }

    public static String getHeader(final HttpServletRequest request, final String name) {
        final String value = request.getHeader(name);
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }
        return urlDecode(value);
    }

    public static Map<String, String> getHeaders(final HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        key -> key,
                        request::getHeader,
                        (o, n) -> n,
                        LinkedCaseInsensitiveMap::new
                ));
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     */
    public static void renderString(final HttpServletResponse response,
                                    final String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            log.error(MessageFormat
                            .format("ServletUtils::renderString({0}, {1})", response, string),
                    e);
        }
    }

    /**
     * 是否是 Ajax 异步请求
     *
     * @param request HttpServletRequest
     */
    public static boolean isAjaxRequest(final HttpServletRequest request) {
        final String accept = request.getHeader("accept");
        if (StringUtils.contains(accept, MediaType.APPLICATION_JSON_VALUE)) {
            return true;
        }

        final String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }
        final String uri = request.getRequestURI();
        if (StringUtils.containsAnyIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }
        final String ajax = request.getParameter("__ajax");
        return StringUtils.containsAnyIgnoreCase(ajax, "json", "xml");
    }

    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(final String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(final String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 设置 WebFlux 模型响应
     *
     * @param response ServerHttpResponse
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(final ServerHttpResponse response,
                                                   final Object value) {
        return webFluxResponseWriter(response, HttpStatus.OK, value);
    }

    /**
     * 设置 WebFlux 模型响应
     *
     * @param response ServerHttpResponse
     * @param status   HTTP 状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(final ServerHttpResponse response,
                                                   final HttpStatus status,
                                                   final Object value) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value);
    }

    /**
     * 设置 WebFlux 模型响应
     *
     * @param response    ServerHttpResponse
     * @param contentType Content-Type
     * @param status      HTTP 状态码
     * @param value       响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(final ServerHttpResponse response,
                                                   final String contentType,
                                                   final HttpStatus status,
                                                   final Object value) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);

        final String result;
        // 如果是  String 不做处理
        // 如果是 R 转换为 String 返回
        // 如果是 其他，则用 R.ok() 包过后返回
        if (value instanceof String) {
            result = (String) value;
        } else if (value instanceof R) {
            result = JsonUtils.to(value);
        } else {
            result = JsonUtils.to(R.ok(value));
        }
        final byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
        final DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(dataBuffer));
    }
}