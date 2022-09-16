package com.service.zerobnb.web.guest.model;

import com.service.zerobnb.web.guest.dto.GuestDto;
import lombok.Data;

public class Auth {

    @Data
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

    @Data
    public static class LogIn {
        private String email;
        private String password;
    }
}
