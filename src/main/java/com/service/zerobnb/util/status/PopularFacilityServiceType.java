package com.service.zerobnb.util.status;

public enum PopularFacilityServiceType {
    /**
     * 주차 가능
     */
    PARKING(1),
    /**
     * 야외 수영장
     */
    OUTDOOR_BEACH(2),
    /**
     * 조식 운영
     */
    BREAKFAST(3),
    /**
     * 와이파이
     */
    WIFI(4),
    /**
     * 객실금연
     */
    NO_SMOKING(5),
    /**
     * 피트니스
     */
    FITNESS(6),
    /**
     * 24시 데스크
     */
    TWENTY_FOUR_HOUR_DESK(7),
    /**
     * 목욕탕
     */
    BATHS(8),
    /**
     * 무료 세탁
     */
    LAUNDRY(9),
    /**
     * 레스토랑
     */
    RESTAURANT(10),
    /**
     * 커피샵
     */
    COFFEE_SHOP(11),
    /**
     * 바
     */
    BAR(12),
    /**
     * 연회장
     */
    BANQUET_HALL(13),
    /**
     * 수화물 보관
     */
    LUGGAGE_STORAGE(14);
    private final int type;

    PopularFacilityServiceType(int type) {
        this.type = type;
    }

    public static PopularFacilityServiceType convert(int type) {
        switch (type) {
            case 1:
                return PARKING;
            case 2:
                return OUTDOOR_BEACH;
            case 3:
                return BREAKFAST;
            case 4:
                return WIFI;
            case 5:
                return NO_SMOKING;
            case 6:
                return FITNESS;
            case 7:
                return TWENTY_FOUR_HOUR_DESK;
            case 8:
                return BATHS;
            case 9:
                return LAUNDRY;
            case 10:
                return RESTAURANT;
            case 11:
                return COFFEE_SHOP;
            case 12:
                return BAR;
            case 13:
                return BANQUET_HALL;
            case 14:
                return LUGGAGE_STORAGE;
        }
        return null;
    }
}
