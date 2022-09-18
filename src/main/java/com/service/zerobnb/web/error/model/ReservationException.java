package com.service.zerobnb.web.error.model;

import com.service.zerobnb.web.error.message.ExceptionMessage;

public class ReservationException extends RuntimeException {

    public ReservationException(String error) {
        super(error);
    }
    public ReservationException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.message());
    }
}
