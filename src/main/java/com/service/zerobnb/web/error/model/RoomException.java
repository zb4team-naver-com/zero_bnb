package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class RoomException extends RuntimeException {

    public RoomException(String error) {
        super(error);
    }
    public RoomException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
