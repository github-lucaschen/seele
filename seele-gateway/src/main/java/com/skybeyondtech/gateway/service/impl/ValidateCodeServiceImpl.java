package com.skybeyondtech.gateway.service.impl;

import com.google.code.kaptcha.Producer;
import com.skybeyondtech.common.core.constant.CacheConstants;
import com.skybeyondtech.common.core.exception.CaptchaException;
import com.skybeyondtech.common.core.util.uuid.IdUtils;
import com.skybeyondtech.common.redis.service.RedisService;
import com.skybeyondtech.gateway.config.properties.CaptchaProperties;
import com.skybeyondtech.gateway.domain.dto.ValidateCodeDTO;
import com.skybeyondtech.gateway.domain.vo.ValidateCodeVO;
import com.skybeyondtech.gateway.enums.CaptchaTypeEnum;
import com.skybeyondtech.gateway.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * 验证码实现处理
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateCodeServiceImpl implements ValidateCodeService {

    private final Producer captchaProducer;

    private final Producer captchaProducerMath;

    private final RedisService<String> redisService;

    private final CaptchaProperties captchaProperties;

    /**
     * 生成验证码
     *
     * @return ValidateCodeVO
     */
    @Override
    public ValidateCodeVO createCaptcha() {
        final Boolean enabled = captchaProperties.getEnabled();
        final ValidateCodeVO.ValidateCodeVOBuilder validateCodeVOBuilder =
                ValidateCodeVO.builder().enabled(enabled);
        // 配置文件中没有开启，直接返回
        if (BooleanUtils.isFalse(enabled)) {
            return validateCodeVOBuilder.build();
        }
        final String uuid = IdUtils.simpleUUID();
        final String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        // 值（文字||计算的值）
        final String code;
        // 图片
        final BufferedImage image;

        if (CaptchaTypeEnum.CHAR == captchaProperties.getType()) {
            final String capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        } else if (CaptchaTypeEnum.MATH == captchaProperties.getType()) {
            final String capText = captchaProducerMath.createText();
            final String capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else {
            return validateCodeVOBuilder.build();
        }
        // 将值存进缓存，后续校验使用
        redisService.stringSet(verifyKey, code, CacheConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        final FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", fastByteArrayOutputStream);
        } catch (IOException error) {
            log.error("验证码图片转换错误", error);
            return validateCodeVOBuilder.build();
        }
        validateCodeVOBuilder
                .uuid(verifyKey)
                .image(Base64.getEncoder().encodeToString(fastByteArrayOutputStream.toByteArray()));
        return validateCodeVOBuilder.build();
    }

    @Override
    public void checkCaptcha(final ValidateCodeDTO validateCodeDTO) {
        final String uuid = validateCodeDTO.getUuid();
        final String code = validateCodeDTO.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new CaptchaException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid)) {
            throw new CaptchaException("验证码已失效");
        }
        final String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        final String value = redisService.stringGet(verifyKey);
        redisService.delete(value);
        if (!StringUtils.equalsIgnoreCase(code, value)) {
            throw new CaptchaException("验证码错误");
        }
    }
}
