package com.service.zerobnb.web.room.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.zerobnb.web.room.model.RoomImageInput;
import com.service.zerobnb.web.room.model.RoomInput;
import com.service.zerobnb.web.room.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = RoomController.class)
class RoomControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerRoom() throws Exception {
        when(roomService.registerRoom(any())).thenReturn(200L);

        mockMvc.perform(post("/room/register").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                RoomInput.builder()
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
                                        .build()
                        )))
                .andExpect(status().isOk());
        verify(roomService, times(1)).registerRoom(any());
    }

    @Test
    void updateRoom() throws Exception {
        when(roomService.updateRoom(any(), any())).thenReturn(200L);

        mockMvc.perform(put("/room/update/3").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                RoomInput.builder()
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
                                        .build()
                        )))
                .andExpect(status().isOk());
        verify(roomService, times(1)).updateRoom(any(), any());
    }

    @Test
    void deleteRoom() throws Exception {
        when(roomService.deleteRoom(any())).thenReturn(200L);

        mockMvc.perform(delete("/room/delete/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(roomService, times(1)).deleteRoom(any());
    }
}