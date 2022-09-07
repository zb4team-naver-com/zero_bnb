package com.service.zerobnb.util.status;


public enum UserStatus {
    /**
     * 이메일 미인증 회원
     */
    NOT_AUTH("NOT_AUTH"),

    /**
     * 활성(이용중) 회원
     */
    ACTIVE("ACTIVE"),

    /**
     * 탈퇴 된 회원
     */
    WITHDRAW("WITHDRAW"),

    /**
     * 정지 된 회원
     */
    STOP("STOP");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }
}
