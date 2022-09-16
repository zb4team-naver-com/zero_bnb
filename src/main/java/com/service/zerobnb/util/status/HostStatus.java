package com.service.zerobnb.util.status;

public enum HostStatus {
    /**
     * 활성(이용중) 회원
     */
    ACTIVE("ACTIVE"),

    /**
     * 비활성화(중단) 회원
     */
    DISABLED("DISABLED");

    private final String status;

    HostStatus(String status) {
        this.status = status;
    }

    public static boolean checkIsActive(HostStatus hostStatus) {
        return hostStatus == HostStatus.ACTIVE ? true : false;
    }
}
