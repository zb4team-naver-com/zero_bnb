package com.service.zerobnb.util.status;

public enum HostStatus {
    /**
     * 활성(이용중) 회원
     */
    ACTIVE("ACTIVE"),

    /**
     * 비활성화(중단) 회원
     */
    WITHDRAW("DISABLED");

    private final String status;

    HostStatus(String status) {
        this.status = status;
    }
}
