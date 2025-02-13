package com.skybeyondtech.common.security.interceptor;

import com.skybeyondtech.common.core.constant.SecurityConstants;
import com.skybeyondtech.common.core.context.SecurityContextHolder;
import com.skybeyondtech.common.core.util.ServletUtils;
import com.skybeyondtech.common.security.domain.dto.LoginUser;
import com.skybeyondtech.common.security.util.AuthUtils;
import com.skybeyondtech.common.security.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID));
        SecurityContextHolder.setUserCode(ServletUtils.getHeader(request,
                SecurityConstants.DETAILS_USER_CODE));
        SecurityContextHolder.setUserKey(ServletUtils.getHeader(request,
                SecurityConstants.USER_KEY));
        SecurityContextHolder.setClientCode(ServletUtils.getHeader(request, SecurityConstants.DETAILS_CLIENT_CODE));

        final String token = SecurityUtils.getToken();
        if (StringUtils.isNoneBlank(token)) {
            final LoginUser loginUser = AuthUtils.getLoginUser(token);
            if (Objects.nonNull(loginUser)) {
                AuthUtils.verifyLoginUserExpire(loginUser);
                SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Object handler,
                                final Exception ex)
            throws Exception {
        SecurityContextHolder.remove();
    }
}
