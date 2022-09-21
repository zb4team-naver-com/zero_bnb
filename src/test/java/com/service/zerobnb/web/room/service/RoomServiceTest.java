package com.service.zerobnb.web.room.service;

import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.accommodation.domain.AccommodationImage;
import com.service.zerobnb.web.accommodation.service.AccommodationService;
import com.service.zerobnb.web.room.domain.Room;
import com.service.zerobnb.web.room.model.RoomImageInput;
import com.service.zerobnb.web.room.model.RoomInput;
import com.service.zerobnb.web.room.repository.RoomImageRepository;
import com.service.zerobnb.web.room.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class RoomServiceTest {
    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private AccommodationService accommodationService;

    @MockBean
    private RoomImageRepository roomImageRepository;

    @Test
    void findRoomsByAccommodationId() {
        when(accommodationService.findAccommodationById(any())).thenReturn(
                Accommodation.builder()
                        .roomList(Arrays.asList(
                                Room.builder().id(1L).accommodation(Accommodation.builder().build()).build(),
                                Room.builder().id(2L).accommodation(Accommodation.builder().build()).build(),
                                Room.builder().id(3L).accommodation(Accommodation.builder().build()).build()
                        ))
                        .build());

        assertDoesNotThrow(() -> roomService.findRoomsByAccommodationId(1L));
        verify(accommodationService, times(1)).findAccommodationById(any());
    }

    @Test
    void findRoomById() {
        when(roomRepository.existsById(any())).thenReturn(true);
        when(roomRepository.findById(any())).thenReturn(Optional.of(Room.builder().build()));
        assertDoesNotThrow(() -> roomService.findRoomById(2L));
        verify(roomRepository, times(1)).existsById(any());
        verify(roomRepository, times(1)).findById(any());
    }

    @Test
    void registerRoom() {
        when(accommodationService.findAccommodationById(any())).thenReturn(Accommodation.builder().build());
        when(roomRepository.saveAll(any())).thenReturn(List.of(Room.builder().build()));
        when(roomRepository.save(any())).thenReturn(Room.builder().build());
        assertDoesNotThrow(() -> roomService.registerRoom(RoomInput.builder()
                .name("델루나 호텔")
                .discount(5000)
                .basicOption("최고급 침대 구비, 넷플릭스 무료 시청 가능")
                .cost(15000)
                .description("핑크빛 향기를 느낄 수 있는 최고급 방입니다.")
                .standardPeople(2)
                .maxPeople(4)
                .count(15)
                .smoke(false)
                .roomImages(Arrays.asList(
                                RoomImageInput.builder().url("https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B0%91%EB%A3%A1.png").build(),
                                RoomImageInput.builder().url("https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B0%91%EB%A3%A1.png").build(),
                                RoomImageInput.builder().url("https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B0%91%EB%A3%A1.png").build(),
                                RoomImageInput.builder().url("https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B0%91%EB%A3%A1.png").build(),
                                RoomImageInput.builder().url("https://zerobnb-bucket.s3.amazonaws.com/image/%EA%B9%80%EA%B0%91%EB%A3%A1.png").build()
                        )
                )
                .accommodationId(1L)
                .build()));
    }
}