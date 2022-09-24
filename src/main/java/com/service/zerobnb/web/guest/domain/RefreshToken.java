package com.service.zerobnb.web.guest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String guestEmail;

    @NotNull
    private String token;

    @Builder
    public RefreshToken(String guestEmail, String token) {
        this.guestEmail = guestEmail;
        this.token = token;
    }

    public static RefreshToken createTokenEntity(String guestEmail, String token) {
        return new RefreshToken(guestEmail, token);
    }
}
