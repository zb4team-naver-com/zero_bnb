package com.service.zerobnb.util.type;

public enum TransportationType {
    /**
     * 도보
     */
    FOOT("foot"),
    /**
     * 차량
     */
    VEHICLE("vehicle");
    private final String type;

    TransportationType(String type) {
        this.type = type;
    }
}
