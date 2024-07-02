package com.skybeyondtech.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginBody {
    /**
     * 用户名
     */
    private String userCode;
    /**
     * 密码
     */
    private String password;
}
