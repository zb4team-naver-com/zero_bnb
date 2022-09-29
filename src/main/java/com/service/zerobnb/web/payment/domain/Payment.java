package com.service.zerobnb.web.payment.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.type.PaymentMethod;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.reservation.model.ReservationForm;
import com.service.zerobnb.web.room.domain.Room;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"reservation"})
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private long reservationCost;

    private long couponCost;

    private long pointCost;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    public static Payment from(ReservationForm form, Room room) {
        return Payment.builder()
                .reservationCost(room.getCost() - form.getPaymentForm().getCouponCost() - form.getPaymentForm().getPointCost())
                .couponCost(form.getPaymentForm().getCouponCost())
                .pointCost(form.getPaymentForm().getPointCost())
                .paymentMethod(form.getPaymentForm().getPaymentMethod())
                .build();
    }
}
