package com.service.zerobnb.web.reservation.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.TransportationType;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.payment.domain.Payment;
import com.service.zerobnb.web.room.domain.Room;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@ToString(exclude = {"guest", "room", "payment"})
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "reservation")
    private Payment payment;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private int days;

    private long cost;

    @Enumerated(EnumType.STRING)
    private TransportationType transportationType;

    private String bookerName;

    private String bookerPhone;
}
