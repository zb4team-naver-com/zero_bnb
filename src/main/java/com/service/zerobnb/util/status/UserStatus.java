package com.service.zerobnb.util.status;


public enum UserStatus {
    /**
     * 이메일 미인증 회원
     */
    ROLE_UNAUTH("NOT_AUTH"),

    /**
     * 활성(이용중) 회원
     */
    ROLE_ACTIVE("ACTIVE"),

    /**
     * 탈퇴 된 회원
     */
    ROLE_WITHDRAW("WITHDRAW"),

    /**
     * 정지 된 회원
     */
    ROLE_STOP("STOP"),

    /**
     * 관리자
     */
    ROLE_ADMIN("ADMIN");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }
}
