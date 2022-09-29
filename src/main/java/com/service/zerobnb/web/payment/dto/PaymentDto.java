package com.service.zerobnb.web.payment.dto;

import com.service.zerobnb.util.type.PaymentMethod;
import com.service.zerobnb.web.payment.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long id;
    private long reservationCost;
    private long couponCost;
    private long pointCost;
    private PaymentMethod paymentMethod;

    public static PaymentDto from(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .reservationCost(payment.getReservationCost())
                .couponCost(payment.getCouponCost())
                .pointCost(payment.getPointCost())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }
}
