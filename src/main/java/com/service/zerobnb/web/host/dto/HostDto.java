package com.service.zerobnb.web.host.dto;

import com.service.zerobnb.util.status.HostStatus;
import com.service.zerobnb.web.host.domain.Host;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HostDto {
    private final long hostId;

    private final String profileImage;

    private final String businessContact;

    private final String companyRegistrationNumber;

    private boolean isActive;

    public static HostDto from(Host host) {
        return HostDto.builder()
                .hostId(host.getId())
                .profileImage(host.getProfileImage())
                .businessContact(host.getBusinessContact())
                .companyRegistrationNumber(host.getCompanyRegistrationNumber())
                .isActive(HostStatus.checkIsActive(host.getStatus()))
                .build();
    }
}
