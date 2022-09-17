package com.service.zerobnb.web.accommodation.dto;

import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.util.LocationPosition;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationForHostDto {
    private Long id;
    private String name;
    private LocationPosition locationPosition;
    private String address;
    private String description;
    private String notice;
    private String policy;
    private int wishCount;
    private AccommodationType accommodationType;

    private List<AccommodationImageDto> accommodationImages;
    private List<EventDto> accommodationEvents;
    private List<PopularFacilityServiceDto> popularFacilityServices;

    public static AccommodationForHostDto from(Accommodation accommodation) {
        return AccommodationForHostDto.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .locationPosition(accommodation.getLocationPosition())
                .address(accommodation.getAddress())
                .description(accommodation.getDescription())
                .notice(accommodation.getNotice())
                .policy(accommodation.getPolicy())
                .accommodationType(accommodation.getAccommodationType())
                .accommodationImages(accommodation.getAccommodationImageList().stream().map(AccommodationImageDto::from).collect(Collectors.toList()))
                .accommodationEvents(accommodation.getEventList().stream().map(EventDto::from).collect(Collectors.toList()))
                .popularFacilityServices(accommodation.getPopularFacilityServiceList().stream().map(PopularFacilityServiceDto::from).collect(Collectors.toList()))
                .build();
    }
}
