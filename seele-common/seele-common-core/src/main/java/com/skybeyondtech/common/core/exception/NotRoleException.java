package com.skybeyondtech.common.core.exception;

import org.apache.commons.lang3.StringUtils;

public class NotRoleException extends RuntimeException {

    public NotRoleException(final String role) {
        super(role);
    }

    public NotRoleException(final String[] roles) {
        super(StringUtils.join(roles, ","));
    }
}