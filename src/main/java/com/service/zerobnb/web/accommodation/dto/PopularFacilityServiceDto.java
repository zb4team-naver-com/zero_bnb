package com.service.zerobnb.web.accommodation.dto;

import com.service.zerobnb.web.accommodation.domain.PopularFacilityService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularFacilityServiceDto {
    private int popularFacilityServiceType;

    public static PopularFacilityServiceDto from(PopularFacilityService popularFacilityService) {
        return PopularFacilityServiceDto.builder()
                .popularFacilityServiceType(popularFacilityService.getPopularFacilityServiceType().getType())
                .build();
    }
}
