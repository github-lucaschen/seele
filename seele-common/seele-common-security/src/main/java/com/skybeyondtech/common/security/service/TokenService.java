package com.skybeyondtech.common.security.service;

import com.skybeyondtech.common.core.constant.CacheConstants;
import com.skybeyondtech.common.core.constant.SecurityConstants;
import com.skybeyondtech.common.core.util.JsonUtils;
import com.skybeyondtech.common.core.util.JwtUtils;
import com.skybeyondtech.common.core.util.ServletUtils;
import com.skybeyondtech.common.core.util.ip.IpUtils;
import com.skybeyondtech.common.core.util.uuid.IdUtils;
import com.skybeyondtech.common.redis.service.RedisService;
import com.skybeyondtech.common.security.domain.dto.LoginUser;
import com.skybeyondtech.common.security.domain.vo.TokenVO;
import com.skybeyondtech.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenService {
    private final RedisService<String> redisService;

    protected static final Long MILLIS_SECOND = 1000L;

    protected static final Long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long EXPIRE_TIME = CacheConstants.EXPIRATION;

    private static final String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private static final Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    /**
     * 创建令牌
     */
    public TokenVO createToken(final LoginUser loginUser) {
        final String token = IdUtils.fastUUID();
        final Long userId = loginUser.getUserId();
        final String userCode = loginUser.getUserCode();

        loginUser.setToken(token);
        loginUser.setLoginIp(IpUtils.getIpAddr());
        refreshToken(loginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USER_CODE, userCode);


        return TokenVO.builder()
                .accessToken(JwtUtils.createToken(claimsMap))
                .expiresIn(EXPIRE_TIME).build();
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser() {
        return getLoginUser(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(final HttpServletRequest request) {
        // 获取请求携带的令牌
        final String token = SecurityUtils.getToken(request);
        return getLoginUser(token);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(final String token) {
        try {
            if (StringUtils.isNotEmpty(token)) {
                final String userKey = JwtUtils.getUserKey(token);
                final String userString = redisService.stringGet(getTokenKey(userKey));
                return JsonUtils.to(userString, LoginUser.class);
            }
        } catch (Exception e) {
            log.error("获取用户信息异常'{}'", e.getMessage());
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(final LoginUser loginUser) {
        if (Objects.nonNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户缓存信息
     */
    public void deleteLoginUser(final String token) {
        if (StringUtils.isNotEmpty(token)) {
            final String userKey = JwtUtils.getUserKey(token);
            redisService.delete(getTokenKey(userKey));
        }
    }

    /**
     * 验证令牌有效期，相差不足 120 分钟，自动刷新缓存
     *
     * @param loginUser
     */
    public void verifyToken(final LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = Instant.now().getEpochSecond();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(final LoginUser loginUser) {
        loginUser.setLoginTime(Instant.now().getEpochSecond());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME * MILLIS_MINUTE);
        // 根据 uuid 将 loginUser 缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.stringSet(userKey, JsonUtils.to(loginUser), EXPIRE_TIME,
                TimeUnit.MINUTES);
    }

    private String getTokenKey(final String token) {
        return ACCESS_TOKEN + token;
    }
}
