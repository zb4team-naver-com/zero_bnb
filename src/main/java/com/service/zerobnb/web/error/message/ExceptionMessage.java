package com.service.zerobnb.web.error.message;

public enum ExceptionMessage {
    NOT_VALID_FILE_NAME("파일 명이 유효하지 않습니다."),
    NOT_VALID_INPUT("입력 값이 유효하지 않습니다."),

    // 게스트, 호스트
    ALREADY_EXIST_GUEST("이미 등록된 이메일입니다."),
    NOT_AUTH_EMAIL("이메일 인증이 필요합니다."),
    NOT_LOGIN_STATUS("로그인 되어있지 않습니다"),
    NOT_EXIST_GUEST("게스트 정보가 존재하지 않습니다"),
    NOT_EXIST_HOST("호스트 정보가 존재하지 않습니다"),
    ALREADY_EXIST_HOST("현재 호스트 정보가 등록 된 상태입니다"),
    DISABLED_HOST("호스트가 비활성화 상태입니다"),

    // 숙소
    NOT_EXIST_ACCOMMODATION("숙소 정보가 존재하지 않습니다"),
    ALREADY_DELETE_ACCOMMODATION("이미 삭제 된 숙소 정보 입니다"),
    NOT_EXIST_ACCOMMODATION_TYPE("존재하지 않는 숙소 타입 입니다."),

    // 방
    NOT_EXIST_ROOM("방 정보가 존재하지 않습니다."),
    ZERO_COUNT_ROOM("방 수량이 소진되었습니다."),
    ALREADY_DELETE_ROOM("이미 삭제 된 방 정보 입니다"),

    // 예약
    EXCEED_MAX_PEOPLE("최대 인원을 초과했습니다."),
    NOT_EXIST_RESERVATION("예약 정보가 존재하지 않습니다."),
    EXPIRE_CANCEL_PERIOD("예약 취소 기간이 지났습니다."),
    ALREADY_EXIST_RESERVATION_DATE("입력한 날짜에 예약이 이미 존재합니다."),

    // 리뷰
    ALREADY_EXIST_REVIEW("이미 작성하신 리뷰입니다."),
    NOT_EXIST_REVIEW("존재하지 않는 리뷰입니다."),
    NOT_EXIST_REVIEW_CATEGORY("존재하지 않는 리뷰 카테고리입니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
