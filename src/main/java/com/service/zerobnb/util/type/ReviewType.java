package com.service.zerobnb.util.type;

public enum ReviewType {
    /**
     * 서비스&친절도
     */
    SERVICE_KINDNESS(1),
    /**
     * 숙소&객실 청결도
     */
    ROOM_CLEANLINESS(2),
    /**
     * 시설 편의성
     */
    FACILITY_CONVENIENCE(3),
    /**
     * 비품&용품 만족도
     */
    SUPPLIES_SATISFACTION(4);
    private final int type;

    ReviewType(int type) {
        this.type = type;
    }
}
