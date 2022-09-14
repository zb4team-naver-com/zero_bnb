package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class ValidationException extends RuntimeException {
    public ValidationException(String error) {
        super(error);
    }

    public ValidationException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
