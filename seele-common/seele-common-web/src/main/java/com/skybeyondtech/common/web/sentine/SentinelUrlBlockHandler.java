package com.skybeyondtech.common.web.sentine;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.skybeyondtech.common.core.domain.R;
import com.skybeyondtech.common.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Sentinel 流控、降级、参数热点、系统、授权 统一拦截处理
 */
@Slf4j
@Configuration
public class SentinelUrlBlockHandler implements BlockExceptionHandler {

    private static final Map<Class<? extends Throwable>, String> EXCEPTION_MAP =
            new HashMap<>(8);

    static {
        // 流控（限流）
        EXCEPTION_MAP.put(FlowException.class, "[SENTINEL] 429 FLOW_EXCEPTION");
        // 降级
        EXCEPTION_MAP.put(DegradeException.class, "[SENTINEL] 430 DEGRADE_EXCEPTION");
        // 参数热点异常
        EXCEPTION_MAP.put(ParamFlowException.class, "[SENTINEL] 431 PARAM_FLOW_EXCEPTION");
        // 系统异常
        EXCEPTION_MAP.put(SystemBlockException.class, "[SENTINEL] 432 SYSTEM_BLOCK_EXCEPTION");
        // 授权异常
        EXCEPTION_MAP.put(AuthorityException.class, "[SENTINEL] 433 AUTHORITY_EXCEPTION");
    }

    @Override
    public void handle(final HttpServletRequest httpServletRequest,
                       final HttpServletResponse httpServletResponse,
                       final BlockException e) throws Exception {
        String errorMessage = "[SENTINEL] 503 SERVICE_UNAVAILABLE";
        Class<? extends Throwable> exceptionClass = e.getClass();
        if (EXCEPTION_MAP.containsKey(exceptionClass)) {
            errorMessage = EXCEPTION_MAP.get(exceptionClass);
        }
        log.error("{}", e.getClass().getSimpleName(), e);
        httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JsonUtils.to(R.fail(errorMessage)));
        writer.flush();
        writer.close();
    }
}