package com.service.zerobnb.web.accommodation.service;

import com.service.zerobnb.util.LocationPosition;
import com.service.zerobnb.util.status.HostStatus;
import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.util.type.PopularFacilityServiceType;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.accommodation.domain.AccommodationImage;
import com.service.zerobnb.web.accommodation.domain.Event;
import com.service.zerobnb.web.accommodation.domain.PopularFacilityService;
import com.service.zerobnb.web.accommodation.dto.AccommodationInfoDto;
import com.service.zerobnb.web.accommodation.model.AccommodationImageInput;
import com.service.zerobnb.web.accommodation.model.AccommodationInput;
import com.service.zerobnb.web.accommodation.model.EventInput;
import com.service.zerobnb.web.accommodation.model.PopularFacilityServiceInput;
import com.service.zerobnb.web.accommodation.repository.AccommodationImageRepository;
import com.service.zerobnb.web.accommodation.repository.AccommodationRepository;
import com.service.zerobnb.web.accommodation.repository.EventRepository;
import com.service.zerobnb.web.accommodation.repository.PopularFacilityServiceRepository;
import com.service.zerobnb.web.error.model.AccommodationException;
import com.service.zerobnb.web.error.model.ValidationException;
import com.service.zerobnb.web.host.domain.Host;
import com.service.zerobnb.web.host.service.HostService;
import com.service.zerobnb.web.room.domain.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static com.service.zerobnb.util.type.AccommodationType.PENSION;
import static com.service.zerobnb.web.error.message.ExceptionMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class AccommodationServiceTest {
    @Autowired
    private AccommodationService accommodationService;

    @MockBean
    private AccommodationRepository accommodationRepository;

    @MockBean
    private AccommodationImageRepository accommodationImageRepository;

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private PopularFacilityServiceRepository popularFacilityServiceRepository;

    @MockBean
    private HostService hostService;

    @Test
    void registerAccommodation() {
        when(hostService.findHostById(any())).thenReturn(Host.builder().id(25L).status(HostStatus.ACTIVE).build());
        when(accommodationRepository.save(any())).thenReturn(Accommodation.builder().build());
        when(accommodationImageRepository.saveAll(any())).thenReturn(Arrays.asList(AccommodationImage.builder().build()));
        when(eventRepository.saveAll(any())).thenReturn(Arrays.asList(Event.builder().build()));
        when(popularFacilityServiceRepository.saveAll(any())).thenReturn(Arrays.asList(PopularFacilityService.builder().build()));

        assertDoesNotThrow(() -> accommodationService.registerAccommodation(AccommodationInput.builder()
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
                .build()));
        verify(hostService, times(2)).findHostById(any());
        verify(accommodationRepository, times(1)).save(any());
        verify(accommodationImageRepository, times(1)).saveAll(any());
        verify(eventRepository, times(1)).saveAll(any());
        verify(popularFacilityServiceRepository, times(1)).saveAll(any());
    }

    @Test
    void deleteAccommodationTest() {
        when(accommodationRepository.existsById(any())).thenReturn(true);
        when(accommodationRepository.findById(any())).thenReturn(Optional.of(Accommodation.builder().build()));
        assertDoesNotThrow(() -> accommodationService.deleteAccommodation(1L));
        verify(accommodationRepository, times(1)).existsById(any());
        verify(accommodationRepository, times(1)).findById(any());
    }

    @Test
    @DisplayName("lat, lnt 중 null 이 입력될 경우 - 주변 숙소 정보 반환 실패")
    void nearDistOrTypeAccommodationTest() {
        // given
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("부산집")
                .locationPosition(LocationPosition.builder()
                        .latitude(36.0)
                        .longitude(34.0)
                        .build())
                .address("부산광역시 해운대구")
                .description("해변가에서 재밌게 노세요~~")
                .accommodationType(PENSION)
                .roomList(Arrays.asList(Room.builder().id(1L)
                        .standardPeople(3).maxPeople(5)
                        .description("안녕").smoke(false).cost(50000).discount(10).build()))
                .accommodationImageList(Arrays.asList(
                        AccommodationImage.builder().url("https://img.com/test1.png").build(),
                        AccommodationImage.builder().url("https://img.com/test2.png").build(),
                        AccommodationImage.builder().url("https://img.com/test3.png").build(),
                        AccommodationImage.builder().url("https://img.com/test4.png").build(),
                        AccommodationImage.builder().url("https://img.com/test5.png").build()
                ))
                .eventList(Arrays.asList(
                        Event.builder().description("넷플릭스 무료 시청 ").build(),
                        Event.builder().description("삼겹살 파티 진행").build(),
                        Event.builder().description("펜션 내 수영장 무료 이용 가능").build()
                ))
                .popularFacilityServiceList(Arrays.asList(
                        PopularFacilityService.builder().popularFacilityServiceType(PopularFacilityServiceType.BAR).build(),
                        PopularFacilityService.builder().popularFacilityServiceType(PopularFacilityServiceType.FITNESS).build(),
                        PopularFacilityService.builder().popularFacilityServiceType(PopularFacilityServiceType.LAUNDRY).build()
                ))
                .build();
        given(accommodationRepository.findById(anyLong()))
                .willReturn(Optional.of(accommodation));

        // when
        ValidationException exception = assertThrows(ValidationException.class,
                () -> accommodationService.nearDistOrTypeAccommodation(null, 37.0, null));

        // then
        assertEquals(NOT_VALID_INPUT.message(), exception.getMessage());
    }

    @Test
    @DisplayName("숙소에 대한 상세 정보를 반환할 경우 - 숙소 상세 정보 반환 성공")
    void accommodationInfoTest() {
        // given
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("부산집")
                .locationPosition(LocationPosition.builder()
                        .latitude(36.0)
                        .longitude(34.0)
                        .build())
                .address("부산광역시 해운대구")
                .description("해변가에서 재밌게 노세요~~")
                .accommodationType(PENSION)
                .roomList(Arrays.asList(Room.builder().id(1L)
                        .standardPeople(3).maxPeople(5)
                        .description("안녕").smoke(false).cost(50000).discount(10).build()))
                .accommodationImageList(Arrays.asList(
                        AccommodationImage.builder().url("https://img.com/test1.png").build(),
                        AccommodationImage.builder().url("https://img.com/test2.png").build(),
                        AccommodationImage.builder().url("https://img.com/test3.png").build(),
                        AccommodationImage.builder().url("https://img.com/test4.png").build(),
                        AccommodationImage.builder().url("https://img.com/test5.png").build()
                ))
                .eventList(Arrays.asList(
                        Event.builder().description("넷플릭스 무료 시청 ").build(),
                        Event.builder().description("삼겹살 파티 진행").build(),
                        Event.builder().description("펜션 내 수영장 무료 이용 가능").build()
                ))
                .popularFacilityServiceList(Arrays.asList(
                        PopularFacilityService.builder().popularFacilityServiceType(PopularFacilityServiceType.BAR).build(),
                        PopularFacilityService.builder().popularFacilityServiceType(PopularFacilityServiceType.FITNESS).build(),
                        PopularFacilityService.builder().popularFacilityServiceType(PopularFacilityServiceType.LAUNDRY).build()
                ))
                .build();
        given(accommodationRepository.findById(anyLong()))
                .willReturn(Optional.of(accommodation));

        // when
        AccommodationInfoDto accommodationInfoDto = accommodationService.accommodationInfo(1L);

        // then
        assertEquals("부산집", accommodationInfoDto.getName());
    }

    @Test
    @DisplayName("숙소 정보가 없을 경우 - 숙소 상세 정보 반환 실패")
    void notExistAccommodationTest() {
        // given
        given(accommodationRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        AccommodationException exception = assertThrows(AccommodationException.class,
                () -> accommodationService.accommodationInfo(2L));

        // then
        assertEquals(NOT_EXIST_ACCOMMODATION.message(), exception.getMessage());
    }
}