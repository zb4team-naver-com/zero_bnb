package com.service.zerobnb.web.payment.model;

import com.service.zerobnb.util.type.PaymentMethod;
import lombok.*;

import javax.validation.constraints.Min;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentForm {

    @Min(0)
    private long couponCost;

    @Min(0)
    private long pointCost;

    private PaymentMethod paymentMethod;
}
