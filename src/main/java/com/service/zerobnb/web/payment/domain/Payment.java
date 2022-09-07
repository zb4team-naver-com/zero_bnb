package com.service.zerobnb.web.payment.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.PaymentMethod;
import com.service.zerobnb.web.reservation.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseTimeEntity {
    @Id
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private long reservationCost;

    private long couponCost;

    private long pointCost;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;
}
