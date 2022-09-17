package com.service.zerobnb.web.accommodation.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.type.PopularFacilityServiceType;
import com.service.zerobnb.web.accommodation.model.PopularFacilityServiceInput;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@ToString(exclude = "accommodation")
@NoArgsConstructor
@AllArgsConstructor
public class PopularFacilityService extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "popular_facility_service_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;


    @Enumerated(EnumType.ORDINAL)
    private PopularFacilityServiceType popularFacilityServiceType;

    public static PopularFacilityService from(PopularFacilityServiceInput popularFacilityServiceInput, Accommodation accommodation) {
        return PopularFacilityService.builder()
                .accommodation(accommodation)
                .popularFacilityServiceType(PopularFacilityServiceType.convert(popularFacilityServiceInput.getPopularFacilityServiceType()))
                .build();
    }
}
