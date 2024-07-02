package com.skybeyondtech.common.security.aspect;

import com.skybeyondtech.common.core.constant.SecurityConstants;
import com.skybeyondtech.common.core.exception.InnerAuthException;
import com.skybeyondtech.common.core.util.ServletUtils;
import com.skybeyondtech.common.security.annotation.InnerAuth;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InnerAuthAspect implements Ordered {
    @Around("@annotation(innerAuth)")
    public Object innerAround(ProceedingJoinPoint point, InnerAuth innerAuth) throws Throwable {
        final String source = ServletUtils.getRequest().getHeader(SecurityConstants.FROM_SOURCE);
        // 内部请求验证
        if (!StringUtils.equals(SecurityConstants.INNER, source)) {
            throw new InnerAuthException("没有内部访问权限，不允许访问");
        }

        final String userId = ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_USER_ID);
        final String userCode =
                ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_USER_CODE);
        // 用户信息验证
        if (innerAuth.isUser() && (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userCode))) {
            throw new InnerAuthException("没有设置用户信息，不允许访问 ");
        }
        return point.proceed();
    }

    /**
     * 确保在权限认证 AOP 执行前执行
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
