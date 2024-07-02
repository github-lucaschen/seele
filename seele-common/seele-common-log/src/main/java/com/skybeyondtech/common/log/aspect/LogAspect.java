package com.skybeyondtech.common.log.aspect;

import com.skybeyondtech.base.client.OperationLogClient;
import com.skybeyondtech.base.domain.dto.OperationLogDTO;
import com.skybeyondtech.common.core.constant.SecurityConstants;
import com.skybeyondtech.common.core.context.SecurityContextHolder;
import com.skybeyondtech.common.core.util.JsonUtils;
import com.skybeyondtech.common.core.util.ip.IpUtils;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.base.enums.ResultTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogAspect {

    private static final int ERROR_LENGTH = 10000;

    private static final int REQUEST_LENGTH = 10000;

    private static final int RESPONSE_LENGTH = 10000;

    private final OperationLogClient operationLogClient;

    private LogAspect logAspectInstance;

    @Lazy
    @Autowired
    public void setLogAspectInstance(final LogAspect logAspect) {
        this.logAspectInstance = logAspect;
    }

    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    /**
     * 计算操作消耗时间
     */
    private StopWatch stopWatch;

    @Pointcut("@annotation(com.skybeyondtech.common.log.annotation.Log)")
    public void logAspect() {
    }

    @Before(value = "logAspect()")
    public void recordLog(final JoinPoint joinPoint) {
        this.stopWatch = StopWatch.createStarted();
    }

    @AfterReturning(value = "logAspect()", returning = "result")
    public void doAfterReturning(final JoinPoint joinPoint, final Object result) {
        handleLog(joinPoint, result, ResultTypeEnum.SUCCESS);
    }

    @AfterThrowing(value = "logAspect()", throwing = "exception")
    public void doAfterThrowing(final JoinPoint joinPoint, final Exception exception) {
        handleLog(joinPoint, exception, ResultTypeEnum.FAIL);
    }

    protected void handleLog(final JoinPoint joinPoint, final Object result,
                             final ResultTypeEnum resultTypeEnum) {
        final Log annotationLog = getAnnotationLog(joinPoint);
        if (Objects.isNull(annotationLog)) {
            return;
        }
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        final HttpServletRequest request = servletRequestAttributes.getRequest();

        final String parameters = JsonUtils.to(Arrays.stream(joinPoint.getArgs())
                .filter(item -> !(item instanceof ServletRequest || item instanceof ServletResponse))
                .collect(Collectors.toList()));


        final String response = resultTypeEnum == ResultTypeEnum.SUCCESS ?
                StringUtils.substringBefore(JsonUtils.to(result), RESPONSE_LENGTH) : StringUtils.EMPTY;
        final String errorMessage =
                resultTypeEnum == ResultTypeEnum.FAIL ?
                        StringUtils.substringBefore(((Exception) result).getMessage()
                                , ERROR_LENGTH) : StringUtils.EMPTY;

        final OperationLogDTO operationLogDTO = OperationLogDTO.builder()
                .userCode(StringUtils.defaultIfBlank(SecurityContextHolder.getUserCode(), "UNKNOWN"))
                .clientCode(StringUtils.defaultIfBlank(SecurityContextHolder.getClientCode(), "PC"))
                .description(annotationLog.description())
                .logType(annotationLog.logType().getCode())
                .operationType(annotationLog.operationType().getCode())
                .className(joinPoint.getSignature().getDeclaringType().getName())
                .method(joinPoint.getTarget().getClass().getSimpleName() + "#" + joinPoint.getSignature().getName())
                .ip(IpUtils.getIpAddr())
                .url(request.getRequestURI())
                .httpMethod(request.getMethod())
                .userAgent(StringUtils.substringBefore(request.getHeader(SecurityConstants.USER_AGENT),
                        REQUEST_LENGTH))
                .parameter(StringUtils.substringBefore(parameters, REQUEST_LENGTH))
                .requestTime(this.stopWatch.getTime())
                .resultType(resultTypeEnum.ordinal())
                .response(response)
                .errorMessage(errorMessage)
                .build();
        logAspectInstance.saveLog(operationLogDTO);
    }

    private Log getAnnotationLog(JoinPoint joinPoint) {
        final Signature signature = joinPoint.getSignature();
        final MethodSignature methodSignature = (MethodSignature) signature;
        final Method method = methodSignature.getMethod();
        if (Objects.nonNull(method)) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    @Async("commonTaskExecutor")
    public void saveLog(final OperationLogDTO operationLogDTO) {
        log.info("[LOG] {}", operationLogDTO);
        operationLogClient.save(operationLogDTO);
    }
}
