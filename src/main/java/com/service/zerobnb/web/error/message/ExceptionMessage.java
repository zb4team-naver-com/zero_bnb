package com.service.zerobnb.web.error.message;

public enum ExceptionMessage {
    NOT_VALID_FILE_NAME("파일 명이 유효하지 않습니다."),
    NOT_VALID_INPUT("입력 값이 유효하지 않습니다."),
    NOT_LOGIN_STATUS("로그인 되어있지 않습니다"),
    NOT_EXIST_GUEST("게스트가 존재하지 않습니다");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
