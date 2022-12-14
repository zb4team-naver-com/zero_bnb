package com.service.zerobnb.web.host.domain;


import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.util.status.HostStatus;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.host.model.HostInput;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@ToString(exclude = "guest")
@NoArgsConstructor
@AllArgsConstructor
public class Host extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "host_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    private String profileImage;

    private String businessContact;

    private String companyRegistrationNumber;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "host")
    private List<Accommodation> accommodationList;

    @Enumerated(EnumType.STRING)
    private HostStatus status;

    public static Host from(HostInput hostInput, Guest guest) {
        return Host.builder()
                .guest(guest)
                .profileImage(hostInput.getProfileImage())
                .businessContact(hostInput.getBusinessContact())
                .companyRegistrationNumber(hostInput.getCompanyRegistrationNumber())
                .status(HostStatus.ACTIVE)
                .build();
    }

    public static Host from(HostInput hostInput, Host host) {
        host.setProfileImage(hostInput.getProfileImage());
        host.setBusinessContact(hostInput.getBusinessContact());
        host.setCompanyRegistrationNumber(hostInput.getCompanyRegistrationNumber());
        return host;
    }
}
