package com.skybeyondtech.common.web.advice;

import com.skybeyondtech.common.core.domain.R;
import com.skybeyondtech.common.core.exception.NotPermissionException;
import com.skybeyondtech.common.core.exception.NotRoleException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 路径异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception exception) {
        return R.fail("[WEB] 404 NO_HANDLER_FOUND_EXCEPTION");
    }

    /**
     * Feign 错误
     *
     * @param request   请求体
     * @param response  相应体
     * @param exception FeignException
     * @return 统一返回值
     */
    @ExceptionHandler(FeignException.class)
    public R feignException(HttpServletRequest request,
                            HttpServletResponse response,
                            FeignException exception) {
        log.error("FeignException [ HOST: {} URL: {} STATUS: {}] ",
                request.getRemoteHost(),
                request.getRequestURL(),
                response.getStatus(),
                exception);
        return R.fail("[WEB] 502 Feign 错误");
    }

    /**
     * 请求方式错误
     *
     * @param exception HttpRequestMethodNotSupportedException
     * @return 统一请求返回值
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handlerHttpRequestMethodNotSupportedException(Exception exception) {
        log.error("HttpRequestMethodNotSupportedException: {}", exception.getMessage(), exception);
        return R.fail("[WEB] 5000 请求方式错误");
    }

    /**
     * 未知参数异常
     *
     * @param exception MissingServletRequestParameterException
     * @return 统一返回值
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleException(final MissingServletRequestParameterException exception) {
        log.error("MissingServletRequestParameterException: {}", exception.getMessage(), exception);
        return R.fail("[WEB] 501 未知参数异常");
    }

    /**
     * 权限码异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public R handleNotPermissionException(final NotPermissionException exception) {
        log.error("NotPermissionException: {}'", exception.getMessage(), exception);
        return R.fail("[AUTH] 403 没有访问权限，请联系管理员授权");
    }

    /**
     * 权限码异常
     */
    @ExceptionHandler(NotRoleException.class)
    public R handleNotRoleException(final NotRoleException exception) {
        log.error("NotRoleException: {}'", exception.getMessage(), exception);
        return R.fail("[AUTH] 403 没有访问权限，请联系管理员授权");
    }

    /**
     * 未知异常
     *
     * @param exception 异常
     * @return 统一返回值
     */
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(final Exception exception) {
        log.error(exception.getMessage(), exception);
        return R.fail("[WEB] 500 " + exception.getMessage());
    }
}
