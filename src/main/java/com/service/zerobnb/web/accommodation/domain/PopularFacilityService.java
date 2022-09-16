package com.service.zerobnb.web.accommodation.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.PopularFacilityServiceType;
import com.service.zerobnb.util.status.UserStatus;
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
}
