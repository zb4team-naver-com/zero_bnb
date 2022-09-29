package com.service.zerobnb.web.payment.model;

import com.service.zerobnb.util.type.PaymentMethod;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentForm {

    private long couponCost;
    private long pointCost;
    private PaymentMethod paymentMethod;
}
