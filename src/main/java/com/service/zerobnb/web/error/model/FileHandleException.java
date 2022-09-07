package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class FileHandleException extends RuntimeException {
    public FileHandleException(String error) {
        super(error);
    }

    public FileHandleException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
