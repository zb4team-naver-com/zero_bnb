package com.service.zerobnb.util.status;

public enum PaymentMethod {
    /**
     * 카드
     */
    CARD(1),
    /**
     * 현금
     */
    CASH(2),
    /**
     * 계좌 이체
     */
    BANK_TRANSFER(3),
    /**
     * 무통장 입금
     */
    DEPOSIT_WITHOUT_PASSBOOK(4),
    /**
     * 어플 페이 (ex: TOSS, NAVER, KAKAO etc ...)
     */
    APP_PAY(5);

    private int type;

    PaymentMethod(int type) {
        this.type = type;
    }
}
