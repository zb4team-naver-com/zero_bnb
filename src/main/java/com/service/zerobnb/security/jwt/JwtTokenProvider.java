package com.service.zerobnb.security.jwt;

import com.service.zerobnb.security.DetailsService;
import com.service.zerobnb.util.status.UserStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final DetailsService detailsService;
//    private final JwtConfig jwtConfig;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
    private static final String KEY_ROLE = "role";

//    @Value("${spring.jwt.secret}")
    private String secretKey = "test-key-test-key-test-key-test-key-test-key-test-key-test-key-test-key";

    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    /**
     * Access Token 생성 메서드
     * @param name 유저의 이름
     * @param role 유저의 상태
     * @return Jwt Access Token
     */
    public String generateAccessToken(String name, UserStatus role) {
        Claims claims = Jwts.claims().setSubject(name);
        claims.put(KEY_ROLE, role);

        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .signWith(key, SignatureAlgorithm.HS512)
                    .setExpiration(expiredDate)
                    .compact();
    }

    /**
     * Refresh Token 생성
     * @param name 유저 이름
     * @return Jwt Refresh Token
     */
    public String generateRefToken(String name) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                    .setSubject(name)
                    .setIssuedAt(now)
                    .signWith(key, SignatureAlgorithm.HS512)
                    .setExpiration(expiredDate)
                    .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = detailsService.loadUserByUsername(getGuestName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getGuestName(String token) {
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) return false;

        var claims = this.parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            // TODO
            return e.getClaims();
        }
    }

}
