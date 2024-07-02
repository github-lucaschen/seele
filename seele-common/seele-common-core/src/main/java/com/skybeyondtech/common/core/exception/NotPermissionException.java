package com.skybeyondtech.common.core.exception;

import org.apache.commons.lang3.StringUtils;

public class NotPermissionException extends RuntimeException {

    public NotPermissionException(final String permission) {
        super(permission);
    }

    public NotPermissionException(final String[] permissions) {
        super(StringUtils.join(permissions, ","));
    }
}