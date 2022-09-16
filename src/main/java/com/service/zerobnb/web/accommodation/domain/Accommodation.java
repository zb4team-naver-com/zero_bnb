package com.service.zerobnb.web.accommodation.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.AccommodationType;
import com.service.zerobnb.util.status.LocationPosition;
import com.service.zerobnb.web.coupon.domain.Coupon;
import com.service.zerobnb.web.host.domain.Host;
import com.service.zerobnb.web.room.domain.Room;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@ToString(exclude = "host")
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    private String name;

    @Embedded
    private LocationPosition locationPosition;

    private String address;

    // TODO 지도 API 사용에 따른 위치 필드 추가될 수도 ...

    @Lob
    private String description;

    @Lob
    private String notice;

    @Lob
    private String policy;

    private int wishCount;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "accommodation")
    private List<Room> roomList;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "accommodation")
    private List<Coupon> couponList;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationImage> accommodationImageList;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "accommodation")
    private List<Event> eventList;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "accommodation")
    private List<PopularFacilityService> popularFacilityServiceList;
}
