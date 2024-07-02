package com.skybeyondtech.common.security.auth;

import com.skybeyondtech.common.core.context.SecurityContextHolder;
import com.skybeyondtech.common.core.exception.NotLoginException;
import com.skybeyondtech.common.core.exception.NotPermissionException;
import com.skybeyondtech.common.core.exception.NotRoleException;
import com.skybeyondtech.common.core.util.SpringUtils;
import com.skybeyondtech.common.security.annotation.RequiresLogin;
import com.skybeyondtech.common.security.annotation.RequiresPermissions;
import com.skybeyondtech.common.security.annotation.RequiresRoles;
import com.skybeyondtech.common.security.domain.dto.LoginUser;
import com.skybeyondtech.common.security.enums.LogicalEnum;
import com.skybeyondtech.common.security.service.TokenService;
import com.skybeyondtech.common.security.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AuthLogic {
    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";

    private final TokenService tokenService = SpringUtils.getBean(TokenService.class);

    /**
     * 会话注销
     */
    public void logout() {
        final String token = SecurityUtils.getToken();
        if (StringUtils.isBlank(token)) {
            return;
        }
        logoutByToken(token);
    }

    /**
     * 会话注销，根据指定Token
     */
    public void logoutByToken(String token) {
        tokenService.deleteLoginUser(token);
    }

    /**
     * 检验用户是否已经登录，如未登录，则抛出异常
     */
    public void checkLogin() {
        getLoginUser();
    }

    /**
     * 获取当前用户缓存信息, 如果未登录，则抛出异常
     *
     * @return 用户缓存信息
     */
    public LoginUser getLoginUser() {
        final String token = SecurityUtils.getToken();
        if (StringUtils.isBlank(token)) {
            throw new NotLoginException("未提供 Token");
        }
        final LoginUser loginUser = SecurityUtils.getLoginUser();
        if (Objects.isNull(loginUser)) {
            throw new NotLoginException("无效的 Token");
        }
        return loginUser;
    }

    /**
     * 获取当前用户缓存信息, 如果未登录，则抛出异常
     *
     * @param token 前端传递的认证信息
     * @return 用户缓存信息
     */
    public LoginUser getLoginUser(final String token) {
        return tokenService.getLoginUser(token);
    }

    /**
     * 验证当前用户有效期, 如果相差不足 120 分钟，自动刷新缓存
     *
     * @param loginUser 当前用户信息
     */
    public void verifyLoginUserExpire(final LoginUser loginUser) {
        tokenService.verifyToken(loginUser);
    }

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermission(final String permission) {
        return hasPermission(listPermission(), permission);
    }

    /**
     * 验证用户是否具备某权限, 如果验证未通过，则抛出异常: NotPermissionException
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public void checkPermission(final String permission) {
        if (!hasPermission(listPermission(), permission)) {
            throw new NotPermissionException(permission);
        }
    }

    /**
     * 根据注解(@RequiresPermissions)鉴权, 如果验证未通过，则抛出异常: NotPermissionException
     *
     * @param requiresPermissions 注解对象
     */
    public void checkPermission(final RequiresPermissions requiresPermissions) {
        SecurityContextHolder.setPermission(StringUtils.join(requiresPermissions.value(), ","));
        if (requiresPermissions.logical() == LogicalEnum.AND) {
            checkPermissionAnd(requiresPermissions.value());
        } else {
            checkPermissionOr(requiresPermissions.value());
        }
    }

    /**
     * 验证用户是否含有指定权限，必须全部拥有
     *
     * @param permissions 权限列表
     */
    public void checkPermissionAnd(final String... permissions) {
        Set<String> permissionList = listPermission();
        for (String permission : permissions) {
            if (!hasPermission(permissionList, permission)) {
                throw new NotPermissionException(permission);
            }
        }
    }

    /**
     * 验证用户是否含有指定权限，只需包含其中一个
     *
     * @param permissions 权限码数组
     */
    public void checkPermissionOr(final String... permissions) {
        Set<String> permissionList = listPermission();
        for (String permission : permissions) {
            if (hasPermission(permissionList, permission)) {
                return;
            }
        }
        if (permissions.length > 0) {
            throw new NotPermissionException(permissions);
        }
    }


    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色标识
     * @return 用户是否具备某角色
     */
    public boolean hasRole(final String role) {
        return hasRole(listRole(), role);
    }

    /**
     * 判断用户是否拥有某个角色, 如果验证未通过，则抛出异常: NotRoleException
     *
     * @param role 角色标识
     */
    public void checkRole(final String role) {
        if (!hasRole(role)) {
            throw new NotRoleException(role);
        }
    }

    /**
     * 根据注解(@RequiresRoles)鉴权
     *
     * @param requiresRoles 注解对象
     */
    public void checkRole(RequiresRoles requiresRoles) {
        if (requiresRoles.logical() == LogicalEnum.AND) {
            checkRoleAnd(requiresRoles.value());
        } else {
            checkRoleOr(requiresRoles.value());
        }
    }

    /**
     * 验证用户是否含有指定角色，必须全部拥有
     *
     * @param roles 角色标识数组
     */
    public void checkRoleAnd(final String... roles) {
        final Set<String> roleList = listRole();
        for (String role : roles) {
            if (!hasRole(roleList, role)) {
                throw new NotRoleException(role);
            }
        }
    }

    /**
     * 验证用户是否含有指定角色，只需包含其中一个
     *
     * @param roles 角色标识数组
     */
    public void checkRoleOr(String... roles) {
        final Set<String> roleList = listRole();
        for (String role : roles) {
            if (hasRole(roleList, role)) {
                return;
            }
        }
        if (roles.length > 0) {
            throw new NotRoleException(roles);
        }
    }

    /**
     * 根据注解(@RequiresLogin)鉴权
     *
     * @param at 注解对象
     */
    public void checkByAnnotation(final RequiresLogin at) {
        this.checkLogin();
    }

    /**
     * 根据注解(@RequiresRoles)鉴权
     *
     * @param at 注解对象
     */
    public void checkByAnnotation(final RequiresRoles at) {
        final String[] roleArray = at.value();
        if (at.logical() == LogicalEnum.AND) {
            this.checkRoleAnd(roleArray);
        } else {
            this.checkRoleOr(roleArray);
        }
    }

    /**
     * 根据注解(@RequiresPermissions)鉴权
     *
     * @param at 注解对象
     */
    public void checkByAnnotation(final RequiresPermissions at) {
        final String[] permissionArray = at.value();
        if (at.logical() == LogicalEnum.AND) {
            this.checkPermissionAnd(permissionArray);
        } else {
            this.checkPermissionOr(permissionArray);
        }
    }

    /**
     * 获取当前账号的角色列表
     *
     * @return 角色列表
     */
    public Set<String> listRole() {
        try {
            final LoginUser loginUser = getLoginUser();
            return loginUser.getRoles();
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    /**
     * 获取当前账号的权限列表
     *
     * @return 权限列表
     */
    public Set<String> listPermission() {
        try {
            LoginUser loginUser = getLoginUser();
            return loginUser.getPermissions();
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    /**
     * 判断是否包含权限
     *
     * @param authorities 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermission(final Collection<String> authorities, final String permission) {
        return authorities.stream().filter(StringUtils::isNoneBlank)
                .anyMatch(x -> ALL_PERMISSION.equals(x) || PatternMatchUtils.simpleMatch(x, permission));
    }

    /**
     * 判断是否包含角色
     *
     * @param roles 角色列表
     * @param role  角色
     * @return 用户是否具备某角色权限
     */
    public boolean hasRole(final Collection<String> roles, final String role) {
        return roles.stream().filter(StringUtils::isNoneBlank)
                .anyMatch(x -> SUPER_ADMIN.equals(x) || PatternMatchUtils.simpleMatch(x, role));
    }

}
