package com.skybeyondtech.common.core.exception;

public class UtilException extends RuntimeException {
    public UtilException(final Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }

    public UtilException(final String message) {
        super(message);
    }

    public UtilException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
