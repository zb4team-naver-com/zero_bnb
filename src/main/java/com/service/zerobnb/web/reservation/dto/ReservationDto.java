package com.service.zerobnb.web.reservation.dto;

import com.service.zerobnb.util.type.TransportationType;
import com.service.zerobnb.web.payment.dto.PaymentDto;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.room.dto.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    private Long id;
    private RoomDto roomdto;
    private PaymentDto paymentDto;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private int days;
    private long cost;
    private int peopleCount;
    private TransportationType transportationType;
    private String bookerName;
    private String bookerPhone;

    public static ReservationDto from(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .roomdto(RoomDto.from(reservation.getRoom()))
                .paymentDto(PaymentDto.from(reservation.getPayment()))
                .checkInTime(reservation.getCheckInTime())
                .checkOutTime(reservation.getCheckOutTime())
                .days(reservation.getDays())
                .cost(reservation.getCost())
                .peopleCount(reservation.getPeopleCount())
                .transportationType(reservation.getTransportationType())
                .bookerName(reservation.getBookerName())
                .bookerPhone(reservation.getBookerPhone())
                .build();
    }
}
