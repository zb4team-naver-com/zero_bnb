package com.service.zerobnb.web.accommodation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.zerobnb.util.LocationPosition;
import com.service.zerobnb.web.accommodation.model.AccommodationImageInput;
import com.service.zerobnb.web.accommodation.model.AccommodationInput;
import com.service.zerobnb.web.accommodation.model.EventInput;
import com.service.zerobnb.web.accommodation.model.PopularFacilityServiceInput;
import com.service.zerobnb.web.accommodation.service.AccommodationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = AccommodationController.class)
@Disabled
class AccommodationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AccommodationService accommodationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerAccommodation() throws Exception {
        when(accommodationService.registerAccommodation(any())).thenReturn(200L);

        mockMvc.perform(post("/accommodation/register").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                AccommodationInput.builder()
                                        .type("pension")
                                        .address("충남천안시동남구병천면가전6길23-1")
                                        .description("사랑과 행복이 넘치는 행복펜션입니다. 언제나 방문환영입니다.")
                                        .locationPosition(LocationPosition.builder()
                                                .latitude(25.55)
                                                .longitude(35.55)
                                                .build())
                                        .name("강준식")
                                        .notice("펜션 내에서 흡연 및 음주는 환영입니다.")
                                        .policy("제1조: 화장실 내 휴지는 휴지통에 버린다.")
                                        .eventInputs(Arrays.asList(
                                                EventInput.builder().description("넷플릭스 무료 시청 ").build(),
                                                EventInput.builder().description("삼겹살 파티 진행").build(),
                                                EventInput.builder().description("펜션 내 수영장 무료 이용 가능").build()
                                        ))
                                        .popularFacilityServiceInputs(Arrays.asList(
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(1).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(2).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(5).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(7).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(8).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(10).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(11).build()
                                        ))
                                        .accommodationImageInputs(Arrays.asList(
                                                AccommodationImageInput.builder().url("https://img.com/test1.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test2.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test3.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test4.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test5.png").build()
                                        ))
                                        .hostId(1L)
                                        .build())))
                .andExpect(status().isOk());
        verify(accommodationService, times(1)).registerAccommodation(any());
    }

    @Test
    void updateAccommodation() throws Exception {
        when(accommodationService.updateAccommodation(any(), any())).thenReturn(200L);

        mockMvc.perform(post("/accommodation/update/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                AccommodationInput.builder()
                                        .type("pension")
                                        .address("충남천안시동남구병천면가전6길23-1")
                                        .description("사랑과 행복이 넘치는 행복펜션입니다. 언제나 방문환영입니다.")
                                        .locationPosition(LocationPosition.builder()
                                                .latitude(25.55)
                                                .longitude(35.55)
                                                .build())
                                        .name("강준식")
                                        .notice("펜션 내에서 흡연 및 음주는 환영입니다.")
                                        .policy("제1조: 화장실 내 휴지는 휴지통에 버린다.")
                                        .eventInputs(Arrays.asList(
                                                EventInput.builder().description("넷플릭스 무료 시청 ").build(),
                                                EventInput.builder().description("삼겹살 파티 진행").build(),
                                                EventInput.builder().description("펜션 내 수영장 무료 이용 가능").build()
                                        ))
                                        .popularFacilityServiceInputs(Arrays.asList(
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(1).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(2).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(5).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(7).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(8).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(10).build(),
                                                PopularFacilityServiceInput.builder().popularFacilityServiceType(11).build()
                                        ))
                                        .accommodationImageInputs(Arrays.asList(
                                                AccommodationImageInput.builder().url("https://img.com/test1.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test2.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test3.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test4.png").build(),
                                                AccommodationImageInput.builder().url("https://img.com/test5.png").build()
                                        ))
                                        .hostId(1L)
                                        .build())))
                .andExpect(status().isOk());
        verify(accommodationService, times(1)).updateAccommodation(any(), any());
    }
}