package com.service.zerobnb.config.security;

import com.service.zerobnb.util.status.GuestStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60;
    private static final String KEY_ROLE = "role";

    @Value("{spring.jwt.secret}")
    private String secretKey;

    /**
     * 토큰 생성 메서드
     * @param email 유저의 이메일
     * @param role 유저의 상태
     * @return jwt 토큰
     */
    public String generateToken(String email, GuestStatus role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put(KEY_ROLE, role);

        var now = new Date();
        var expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expiredDate)
                    .signWith(SignatureAlgorithm.HS512, this.secretKey)
                    .compact();
    }

    public String getMail(String token) {
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) return false;

        var claims = this.parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            // TODO
            return e.getClaims();
        }
    }
}
