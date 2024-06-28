package com.skybeyondtech.common.security.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PreAuthorizeAspect {

    @Pointcut(" @annotation(com.skybeyondtech.common.security.annotation.RequiresLogin) || "
            + "@annotation(com.skybeyondtech.common.security.annotation.RequiresPermissions) || "
            + "@annotation(com.skybeyondtech.common.security.annotation.RequiresRoles)")
    public void pointcut() {
    }


}
