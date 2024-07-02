package com.skybeyondtech.common.web.feign;

import com.skybeyondtech.common.core.constant.SecurityConstants;
import com.skybeyondtech.common.core.util.ServletUtils;
import com.skybeyondtech.common.core.util.ip.IpUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(final RequestTemplate requestTemplate) {

        final HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        if (Objects.nonNull(httpServletRequest)) {
            final Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
            // 传递用户信息请求头，防止丢失
            String userId = headers.get(SecurityConstants.DETAILS_USER_ID);
            if (StringUtils.isNotEmpty(userId)) {
                requestTemplate.header(SecurityConstants.DETAILS_USER_ID, userId);
            }
            String userKey = headers.get(SecurityConstants.USER_KEY);
            if (StringUtils.isNotEmpty(userKey)) {
                requestTemplate.header(SecurityConstants.USER_KEY, userKey);
            }
            String userCode = headers.get(SecurityConstants.DETAILS_USER_CODE);
            if (StringUtils.isNotEmpty(userCode)) {
                requestTemplate.header(SecurityConstants.DETAILS_USER_CODE, userCode);
            }
            String authentication = headers.get(SecurityConstants.AUTHORIZATION_HEADER);
            if (StringUtils.isNotEmpty(authentication)) {
                requestTemplate.header(SecurityConstants.AUTHORIZATION_HEADER, authentication);
            }
            // 配置客户端IP
            requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr());
        }
    }
}