package com.service.zerobnb.web.guest.model;

import com.service.zerobnb.web.guest.dto.GuestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Auth {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUp {
        private String email;
        private String password;
        private String name;
        private String birth;
        private String phone;

        public GuestDto of() {
            return GuestDto.builder()
                        .email(this.email)
                        .password(this.password)
                        .name(this.name)
                        .birth(this.birth)
                        .phone(this.phone)
                        .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LogIn {
        private String email;
        private String password;
    }

}
