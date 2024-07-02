package com.skybeyondtech.common.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userCode;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;
}
