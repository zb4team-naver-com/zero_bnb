package com.service.zerobnb.web.accommodation.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.util.LocationPosition;
import com.service.zerobnb.web.accommodation.model.AccommodationInput;
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

    private  boolean is_delete;

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

    public static Accommodation from(AccommodationInput accommodationInput, Host host) {
        return Accommodation.builder()
                .host(host)
                .name(accommodationInput.getName())
                .locationPosition(accommodationInput.getLocationPosition())
                .address(accommodationInput.getAddress())
                .description(accommodationInput.getDescription())
                .notice(accommodationInput.getNotice())
                .policy(accommodationInput.getPolicy())
                .wishCount(0)
                .accommodationType(AccommodationType.convert(accommodationInput.getType()))
                .build();
    }
}
