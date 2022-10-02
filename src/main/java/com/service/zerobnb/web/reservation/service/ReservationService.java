package com.service.zerobnb.web.reservation.service;

import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.error.model.ReservationException;
import com.service.zerobnb.web.error.model.RoomException;
import com.service.zerobnb.web.error.model.ValidationException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import com.service.zerobnb.web.payment.domain.Payment;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.reservation.dto.ReservationDto;
import com.service.zerobnb.web.reservation.model.ReservationForm;
import com.service.zerobnb.web.reservation.repository.ReservationRepository;
import com.service.zerobnb.web.room.domain.Room;
import com.service.zerobnb.web.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.service.zerobnb.web.error.message.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ReservationDto reservation(String email, ReservationForm form) {
        Guest guest = existGuestException(email);

        Room room = roomRepository.findById(form.getRoomId())
                .orElseThrow(() -> new RoomException(NOT_EXIST_ROOM));
        roomException(room, form);

        boolean exists = reservationRepository.existsByGuestIdAndCheckInTimeBetween(guest.getId(),
                form.getCheckInTime(), form.getCheckOutTime());
        if (exists) {
            throw new ReservationException(ALREADY_EXIST_RESERVATION_DATE);
        }

        if (guest.getPoint() - form.getPaymentForm().getPointCost() < 0) {
            throw new GuestException(USE_POINT_GREATER_THAN_GUEST_POINT);
        }
        guest.setPoint(guest.getPoint() - form.getPaymentForm().getPointCost());
        guestRepository.save(guest);

        room.setRoomCount(room.getRoomCount() - 1);
        roomRepository.save(room);

        Payment payment = Payment.from(form, room);

        Reservation reservation = Reservation.from(form, guest, room, payment);
        payment.setReservation(reservation);
        reservationRepository.save(reservation);

        return ReservationDto.from(reservation);
    }

    public List<ReservationDto> myReservation(Long guestId, String email) {
        Guest guest = existGuestException(email);
        if (!guest.getId().equals(guestId)) {
            throw new ValidationException(NOT_VALID_INPUT);
        }

        return guest.getReservationList().stream()
                .map(ReservationDto::from).collect(Collectors.toList());
    }

    @Transactional
    public void cancelReservation(String email, Long reservationId) {
        Guest guest = existGuestException(email);

        Reservation reservation = reservationRepository.findByGuestIdAndId(guest.getId(), reservationId)
                .orElseThrow(() -> new ReservationException(NOT_EXIST_RESERVATION));

        if (reservation.getCheckInTime().isBefore(LocalDateTime.now().plusDays(7))) {
            throw new ReservationException(EXPIRE_CANCEL_PERIOD);
        }

        guest.setPoint(guest.getPoint() + reservation.getPayment().getPointCost());
        guestRepository.save(guest);
        reservationRepository.delete(reservation);
    }

    public Guest existGuestException(String email) {
        return guestRepository.findByEmail(email)
                .orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));
    }

    private void roomException(Room room, ReservationForm reservationForm) {
        if (room.getRoomCount() == 0) {
            throw new RoomException(ZERO_COUNT_ROOM);
        }

        if (room.getMaxPeople() < reservationForm.getPeopleCount()) {
            throw new RoomException(EXCEED_MAX_PEOPLE);
        }
    }
}