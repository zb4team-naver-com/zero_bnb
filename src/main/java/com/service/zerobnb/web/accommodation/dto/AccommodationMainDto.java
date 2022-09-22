package com.service.zerobnb.web.accommodation.dto;

import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.web.accommodation.domain.AccommodationImage;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationMainDto {
    private Long accommodationId;
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private String description;
    private int wishCount;
    private AccommodationType accommodationType;
    private List<AccommodationImage> accommodationImageList;
}
