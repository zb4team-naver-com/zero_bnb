package com.service.zerobnb.web.reservation.service;

import com.service.zerobnb.util.LocationPosition;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.error.model.ReservationException;
import com.service.zerobnb.web.error.model.RoomException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import com.service.zerobnb.web.payment.domain.Payment;
import com.service.zerobnb.web.payment.model.PaymentForm;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.reservation.dto.ReservationDto;
import com.service.zerobnb.web.reservation.model.ReservationForm;
import com.service.zerobnb.web.reservation.repository.ReservationRepository;
import com.service.zerobnb.web.room.domain.Room;
import com.service.zerobnb.web.room.repository.RoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.service.zerobnb.util.status.GuestStatus.ACTIVE;
import static com.service.zerobnb.util.type.AccommodationType.GUEST_HOUSE;
import static com.service.zerobnb.util.type.PaymentMethod.CARD;
import static com.service.zerobnb.util.type.TransportationType.VEHICLE;
import static com.service.zerobnb.web.error.message.ExceptionMessage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private GuestRepository guestRepository;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    @DisplayName("게스트가 예약을 할 경우 - 예약 성공")
    void reservationTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .status(ACTIVE)
                .point(500L)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("경남 게스트 하우스")
                .locationPosition(new LocationPosition(25.5, 30.0))
                .address("경남 김해시")
                .description("자연의 풍경을 즐겨주세요.")
                .accommodationType(GUEST_HOUSE)
                .build();
        Room room = Room.builder()
                .id(1L)
                .standardPeople(2)
                .maxPeople(3)
                .roomCount(5)
                .cost(100000)
                .accommodation(accommodation)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));
        given(reservationRepository.save(any()))
                .willReturn(Reservation.builder()
                        .id(1L)
                        .guest(guest)
                        .room(room)
                        .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                        .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                        .days(5)
                        .cost(100000)
                        .peopleCount(3)
                        .transportationType(VEHICLE)
                        .bookerName(guest.getName())
                        .bookerPhone(guest.getPhone())
                        .build());

        // when
        PaymentForm paymentForm = PaymentForm.builder()
                .couponCost(1000)
                .pointCost(500)
                .paymentMethod(CARD)
                .build();
        ReservationForm form = ReservationForm.builder()
                .roomId(1L)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .peopleCount(3)
                .transportationType(VEHICLE)
                .paymentForm(paymentForm)
                .build();
        ReservationDto reservation = reservationService.reservation("abc@naver.com", form);

        // then
        assertEquals(98500, reservation.getCost());
        assertEquals(98500, reservation.getPaymentDto().getReservationCost());
        assertEquals(3, reservation.getPeopleCount());
        assertEquals("홍길동", reservation.getBookerName());
        assertEquals("010-1111-2222", reservation.getBookerPhone());
        assertEquals(0L, guest.getPoint());
        assertEquals(VEHICLE, reservation.getTransportationType());
        assertEquals(1000, reservation.getPaymentDto().getCouponCost());
        assertEquals(500, reservation.getPaymentDto().getPointCost());
        assertEquals(CARD, reservation.getPaymentDto().getPaymentMethod());
    }

    @Test
    @DisplayName("게스트가 존재하지 않을 경우 - 예약 실패")
    void notExistGuestTest() {
        // given
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.empty());

        // when
        ReservationForm form = ReservationForm.builder()
                .roomId(1L)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .peopleCount(3)
                .transportationType(VEHICLE)
                .build();
        GuestException exception = assertThrows(GuestException.class,
                () -> reservationService.reservation("abc@naver.com", form));

        // then
        assertEquals(NOT_EXIST_GUEST.message(), exception.getMessage());
    }

    @Test
    @DisplayName("방이 존재하지 않을 경우 - 예약 실패")
    void notExistRoomTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        ReservationForm form = ReservationForm.builder()
                .roomId(1L)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .peopleCount(3)
                .transportationType(VEHICLE)
                .build();
        RoomException exception = assertThrows(RoomException.class,
                () -> reservationService.reservation("abc@naver.com", form));

        // then
        assertEquals(NOT_EXIST_ROOM.message(), exception.getMessage());
    }

    @Test
    @DisplayName("방의 개수가 0일 경우 - 예약 실패")
    void zeroCountRoomTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));

        Room room = Room.builder()
                .id(1L)
                .standardPeople(2)
                .maxPeople(3)
                .roomCount(0)
                .cost(100000)
                .discount(10)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));

        // when
        ReservationForm form = ReservationForm.builder()
                .roomId(1L)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .peopleCount(3)
                .transportationType(VEHICLE)
                .build();
        RoomException exception = assertThrows(RoomException.class,
                () -> reservationService.reservation("abc@naver.com", form));

        // then
        assertEquals(ZERO_COUNT_ROOM.message(), exception.getMessage());
    }

    @Test
    @DisplayName("방 최대 인원을 초과할 경우 - 예약 실패")
    void exceedMaxPeopleTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));

        Room room = Room.builder()
                .id(1L)
                .standardPeople(2)
                .maxPeople(3)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));

        // when
        ReservationForm form = ReservationForm.builder()
                .roomId(1L)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .peopleCount(5)
                .transportationType(VEHICLE)
                .build();
        RoomException exception = assertThrows(RoomException.class,
                () -> reservationService.reservation("abc@naver.com", form));

        // then
        assertEquals(EXCEED_MAX_PEOPLE.message(), exception.getMessage());
    }

    @Test
    @DisplayName("사용 포인트가 게스트 포인트보다 많은 경우 - 예약 실패")
    void usePointGreaterThanGuestPointTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .status(ACTIVE)
                .point(0L)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("경남 게스트 하우스")
                .locationPosition(new LocationPosition(25.5, 30.0))
                .address("경남 김해시")
                .description("자연의 풍경을 즐겨주세요.")
                .accommodationType(GUEST_HOUSE)
                .build();
        Room room = Room.builder()
                .id(1L)
                .standardPeople(2)
                .maxPeople(3)
                .roomCount(5)
                .cost(100000)
                .accommodation(accommodation)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));
        given(reservationRepository.save(any()))
                .willReturn(Reservation.builder()
                        .id(1L)
                        .guest(guest)
                        .room(room)
                        .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                        .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                        .days(5)
                        .cost(100000)
                        .peopleCount(3)
                        .transportationType(VEHICLE)
                        .bookerName(guest.getName())
                        .bookerPhone(guest.getPhone())
                        .build());

        // when
        PaymentForm paymentForm = PaymentForm.builder()
                .couponCost(1000)
                .pointCost(500)
                .paymentMethod(CARD)
                .build();
        ReservationForm form = ReservationForm.builder()
                .roomId(1L)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .peopleCount(3)
                .transportationType(VEHICLE)
                .paymentForm(paymentForm)
                .build();
        GuestException exception = assertThrows(GuestException.class,
                () -> reservationService.reservation("abc@naver.com", form));

        // then
        assertEquals(USE_POINT_GREATER_THAN_GUEST_POINT.message(), exception.getMessage());
    }

    @Test
    @DisplayName("예약을 취소할 경우 경우 - 예약 취소 성공")
    void cancelReservationTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .point(500L)
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));

        Room room = Room.builder()
                .id(1L)
                .standardPeople(2)
                .maxPeople(3)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));
        Reservation reservation = Reservation.builder()
                                .id(1L)
                                .guest(guest)
                                .room(room)
                                .payment(Payment.builder()
                                        .id(1L)
                                        .reservationCost(90000)
                                        .pointCost(500L)
                                        .couponCost(0L)
                                        .build())
                                .checkInTime(LocalDateTime.of(2222, 9, 26, 0, 0, 0))
                                .checkOutTime(LocalDateTime.of(2222, 9, 30, 0, 0, 0))
                                .days(5)
                                .cost(90000)
                                .peopleCount(3)
                                .transportationType(VEHICLE)
                                .bookerName(guest.getName())
                                .bookerPhone(guest.getPhone())
                                .build();
        given(reservationRepository.findByGuestIdAndId(anyLong(), anyLong()))
                .willReturn(Optional.of(reservation));

        // when
        reservationService.cancelReservation("abc@naver.com", 1L);

        // then
        assertEquals(1000L, guest.getPoint());
    }

    @Test
    @DisplayName("예약 정보가 없을 경우 - 예약 취소 실패")
    void notExistReservationTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));

        // when
        ReservationException exception = assertThrows(ReservationException.class,
                () -> reservationService.cancelReservation("abc@naver.com", 1L));

        // then
        assertEquals(NOT_EXIST_RESERVATION.message(), exception.getMessage());
    }

    @Test
    @DisplayName("취소 기간이 지났을 경우 - 예약 취소 실패")
    void expireCancelPeriodTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc@naver.com")
                .name("홍길동")
                .phone("010-1111-2222")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));

        Room room = Room.builder()
                .id(1L)
                .standardPeople(2)
                .maxPeople(3)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));
        Reservation reservation = Reservation.builder()
                .id(1L)
                .guest(guest)
                .room(room)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .days(5)
                .cost(90000)
                .peopleCount(3)
                .transportationType(VEHICLE)
                .bookerName(guest.getName())
                .bookerPhone(guest.getPhone())
                .build();
        given(reservationRepository.findByGuestIdAndId(anyLong(), anyLong()))
                .willReturn(Optional.of(reservation));

        // when
        ReservationException exception = assertThrows(ReservationException.class,
                () -> reservationService.cancelReservation("abc@naver.com", 1L));

        // then
        assertEquals(EXPIRE_CANCEL_PERIOD.message(), exception.getMessage());
    }
}