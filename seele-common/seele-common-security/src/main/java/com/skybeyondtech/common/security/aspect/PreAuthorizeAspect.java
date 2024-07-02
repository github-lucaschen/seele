package com.skybeyondtech.common.security.aspect;

import com.skybeyondtech.common.security.annotation.RequiresLogin;
import com.skybeyondtech.common.security.annotation.RequiresPermissions;
import com.skybeyondtech.common.security.annotation.RequiresRoles;
import com.skybeyondtech.common.security.util.AuthUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PreAuthorizeAspect {

    @Pointcut(" @annotation(com.skybeyondtech.common.security.annotation.RequiresLogin) || "
            + "@annotation(com.skybeyondtech.common.security.annotation.RequiresPermissions) || "
            + "@annotation(com.skybeyondtech.common.security.annotation.RequiresRoles)")
    public void pointcut() {
    }

    /**
     * 环绕切入
     *
     * @param joinPoint 切面对象
     * @return 底层方法执行后的返回值
     * @throws Throwable 底层方法抛出的异常
     */
    @Around("pointcut()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        // 注解鉴权
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        // 执行原有逻辑
        return joinPoint.proceed();
    }

    /**
     * 对一个Method对象进行注解检查
     */
    public void checkMethodAnnotation(final Method method) {
        // 校验 @RequiresLogin 注解
        final RequiresLogin requiresLogin = method.getAnnotation(RequiresLogin.class);
        if (requiresLogin != null) {
            AuthUtils.checkLogin();
        }

        // 校验 @RequiresRoles 注解
        final RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);
        if (requiresRoles != null) {
            AuthUtils.checkRole(requiresRoles);
        }

        // 校验 @RequiresPermissions 注解
        final RequiresPermissions requiresPermissions =
                method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            AuthUtils.checkPermission(requiresPermissions);
        }
    }
}
