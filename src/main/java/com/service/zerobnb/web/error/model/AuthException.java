package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class AuthException extends RuntimeException {
    public AuthException(String error) {
        super(error);
    }

    public AuthException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
