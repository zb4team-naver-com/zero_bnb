package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class AccommodationException extends RuntimeException{
    public AccommodationException(String error) {
        super(error);
    }

    public AccommodationException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
