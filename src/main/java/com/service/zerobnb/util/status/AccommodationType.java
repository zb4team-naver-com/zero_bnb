package com.service.zerobnb.util.status;

public enum AccommodationType {

    /**
     * 팬션
     */
    PENSION("pension"),
    /**
     * 호텔
     */
    HOTEL("hotel"),
    /**
     * 모텔
     */
    MOTEL("motel"),
    /**
     * 빌라
     */
    VILLA("villa"),
    /**
     * 게스트 하우스
     */
    GUEST_HOUSE("guest_house");
    private final String type;

    AccommodationType(String type) {
        this.type = type;
    }
}
