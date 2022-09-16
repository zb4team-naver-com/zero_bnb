package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class HostException extends RuntimeException{
    public HostException(String error) {
        super(error);
    }

    public HostException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
