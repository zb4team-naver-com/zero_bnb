package com.service.zerobnb.web.accommodation.dto;

import com.service.zerobnb.web.accommodation.domain.AccommodationImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationImageDto {
    private String accommodationImage;

    public static AccommodationImageDto from(AccommodationImage accommodationImage) {
        return AccommodationImageDto.builder()
                .accommodationImage(accommodationImage.getUrl())
                .build();
    }
}
