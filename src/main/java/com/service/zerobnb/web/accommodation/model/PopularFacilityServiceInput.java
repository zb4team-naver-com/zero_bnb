package com.service.zerobnb.web.accommodation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularFacilityServiceInput {
    private int popularFacilityServiceType;
}
