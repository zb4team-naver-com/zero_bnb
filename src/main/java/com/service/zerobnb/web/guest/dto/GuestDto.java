package com.service.zerobnb.web.guest.dto;

import com.service.zerobnb.util.status.UserStatus;
import com.service.zerobnb.web.guest.domain.Guest;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GuestDto {
    private String email;

    private String password;

    private String name;

    private String birth;

    private String phone;

    private String profileImage;

    private UserStatus status;

    private String emailAuthKey;

    private Long point;

    private boolean isHost;

    @Builder
    public GuestDto(String email, String password, String name, String birth, String phone,
        String profileImage, UserStatus status, String emailAuthKey, Long point,
        boolean isHost) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.profileImage = profileImage;
        this.status = UserStatus.ROLE_UNAUTH;
        this.emailAuthKey = UUID.randomUUID().toString();
        this.point = 0L;
        this.isHost = false;
    }

    public Guest toEntity() {
        return Guest.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .birth(this.birth)
                .phone(this.phone)
                .status(this.status)
                .emailAuthKey(this.emailAuthKey)
                .profileImage(this.profileImage)
                .point(this.point)
                .isHost(this.isHost)
                .build();
    }
}
