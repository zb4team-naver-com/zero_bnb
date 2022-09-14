package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class GuestException extends RuntimeException {
    public GuestException(String error) {
        super(error);
    }

    public GuestException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
