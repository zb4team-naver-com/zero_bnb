package com.service.zerobnb.web.error.message;

public enum ExceptionMessage {
    NOT_VALID_FILE_NAME("파일 명이 유효하지 않습니다."),
    NOT_VALID_INPUT("입력 값이 유효하지 않습니다."),
    NOT_LOGIN_STATUS("로그인 되어있지 않습니다"),
    NOT_EXIST_GUEST("게스트 정보가 존재하지 않습니다"),
    NOT_EXIST_HOST("호스트 정보가 존재하지 않습니다"),
    NOT_EXIST_ACCOMMODATION("숙소 정보가 존재하지 않습니다"),
    ALREADY_EXIST_HOST("현재 호스트 정보가 등록 된 상태입니다"),
    DISABLED_HOST("호스트가 비활성화 상태입니다"),

    ALREADY_DELETE_ACCOMMODATION("이미 삭제 된 숙소 정보 입니다");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
