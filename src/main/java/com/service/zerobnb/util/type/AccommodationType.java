package com.service.zerobnb.util.type;

public enum AccommodationType {

    /**
     * 팬션
     */
    PENSION("PENSION"),
    /**
     * 호텔
     */
    HOTEL("HOTEL"),
    /**
     * 모텔
     */
    MOTEL("MOTEL"),
    /**
     * 빌라
     */
    VILLA("VILLA"),
    /**
     * 게스트 하우스
     */
    GUEST_HOUSE("GUEST_HOUSE");
    private final String type;

    AccommodationType(String type) {
        this.type = type;
    }

    public static AccommodationType convert(String type) {
        switch (type) {
            case "PENSION":
                return PENSION;
            case "HOTEL":
                return HOTEL;
            case "MOTEL":
                return MOTEL;
            case "VILLA":
                return VILLA;
            case "GUEST_HOUSE":
                return GUEST_HOUSE;
        }
        return null;
    }
}
