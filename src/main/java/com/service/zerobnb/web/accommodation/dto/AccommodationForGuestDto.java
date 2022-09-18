package com.service.zerobnb.web.accommodation.dto;

import com.service.zerobnb.util.LocationPosition;
import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccommodationForGuestDto {
    private Long id;
    private String name;
    private LocationPosition locationPosition;
    private String address;
    private String description;
    private int wishCount;
    private AccommodationType accommodationType;


    public static AccommodationForGuestDto from(Accommodation accommodation) {
        return AccommodationForGuestDto.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .locationPosition(accommodation.getLocationPosition())
                .address(accommodation.getAddress())
                .description(accommodation.getDescription())
                .accommodationType(accommodation.getAccommodationType())
                .build();
    }
}
