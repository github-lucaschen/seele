package com.skybeyondtech.gateway.service;

import com.skybeyondtech.gateway.domain.dto.ValidateCodeDTO;
import com.skybeyondtech.gateway.domain.vo.ValidateCodeVO;

/**
 * 验证码
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    public ValidateCodeVO createCaptcha();

    /**
     * 校验验证码
     */
    public void checkCaptcha(final ValidateCodeDTO validateCodeDTO);
}
