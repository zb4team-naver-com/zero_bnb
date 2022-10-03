package com.service.zerobnb.web.reservation.model;


import com.service.zerobnb.util.type.TransportationType;
import com.service.zerobnb.web.payment.model.PaymentForm;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationForm {

    private Long roomId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private int peopleCount;
    private TransportationType transportationType;
    private PaymentForm paymentForm;

    public int getDays(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return (int) checkInTime.until(checkOutTime, ChronoUnit.DAYS);
    }
}
