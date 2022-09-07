package com.service.zerobnb.web.error.message;

public enum ExceptionMessage {
    NOT_VALID_FILE_NAME("파일 명이 유효하지 않습니다.");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
