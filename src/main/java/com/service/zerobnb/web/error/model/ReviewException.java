package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class ReviewException extends RuntimeException {

    public ReviewException(String error) {
        super(error);
    }
    public ReviewException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
