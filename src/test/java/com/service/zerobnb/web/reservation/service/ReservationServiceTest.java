package com.service.zerobnb.web.reservation.service;

import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.error.model.ReservationException;
import com.service.zerobnb.web.error.model.RoomException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.reservation.model.ReservationForm;
import com.service.zerobnb.web.reservation.repository.ReservationRepository;
import com.service.zerobnb.web.room.domain.Room;
import com.service.zerobnb.web.room.repository.RoomRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.service.zerobnb.util.status.GuestStatus.ACTIVE;
import static com.service.zerobnb.util.type.TransportationType.VEHICLE;
import static com.service.zerobnb.web.error.message.ExceptionMessage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Disabled
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
        given(reservationRepository.save(any()))
                .willReturn(Reservation.builder()
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
                        .build());

        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);

        // when
        ReservationForm form = ReservationForm.builder()
                .roomId(1L)
                .checkInTime(LocalDateTime.of(2022, 9, 20, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022, 9, 25, 0, 0, 0))
                .peopleCount(3)
                .transportationType(VEHICLE)
                .build();
        reservationService.reservation("abc@naver.com", form);

        // then
        verify(reservationRepository, times(1)).save(captor.capture());
        assertEquals(90000, captor.getValue().getCost());
        assertEquals(3, captor.getValue().getPeopleCount());
        assertEquals("홍길동", captor.getValue().getBookerName());
        assertEquals("010-1111-2222", captor.getValue().getBookerPhone());
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
    @DisplayName("예약을 취소할 경우 경우 - 예약 취소 성공")
    void cancelReservationTest() {
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
                                .checkInTime(LocalDateTime.of(2022, 9, 26, 0, 0, 0))
                                .checkOutTime(LocalDateTime.of(2022, 9, 30, 0, 0, 0))
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