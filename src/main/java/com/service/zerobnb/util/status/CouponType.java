package com.service.zerobnb.util.status;


public enum CouponType {
    /**
     * 대실
     */
    RENT("rent"),

    /**
     * 숙박
     */
    LODGMENT("lodgment"),

    /**
     * 제한 없음
     */
    ALL("all");
    private final String type;

    CouponType(String type) {
        this.type = type;
    }
}
