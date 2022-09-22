package com.service.zerobnb.web.guest.service;

import com.service.zerobnb.component.MailComponents;
import com.service.zerobnb.security.jwt.JwtTokenProvider;
import com.service.zerobnb.util.status.GuestStatus;
import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.domain.RefreshToken;
import com.service.zerobnb.web.guest.dto.GuestDto;
import com.service.zerobnb.web.guest.dto.ResponseTokenDto;
import com.service.zerobnb.web.guest.model.Auth.LogIn;
import com.service.zerobnb.web.guest.model.Auth.LogOut;
import com.service.zerobnb.web.guest.model.Auth.SignUp;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import com.service.zerobnb.web.guest.repository.RefreshTokenRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final GuestRepository guestRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailComponents mailComponents;
    private final JwtTokenProvider jwtTokenProvider;

    private final String EMAIL_SUBJECT = "zero bnb 가입 인증 메일입니다.";
    private final String EMAIL_TEXT = "<p> 아래 링크를 통해 가입을 완료하세요. </p> <div><a href='http://localhost:8000/signup/email-auth/";

    /**
     * 회원 가입을 요청한 유저를 db에 저장한 후 인증을 위한 이메일을 전송합니다.
     * @param request controller 를 통해 받은 유저 정보
     * @return 성공적으로 저장이 완료된 유저 정보
     */
    public GuestDto register(SignUp request) {

        boolean exists = this.guestRepository.existsByEmail(request.getEmail());

        if (exists) {
            throw new GuestException(ExceptionMessage.ALREADY_EXIST_GUEST);
        }

        GuestDto guestDto = GuestDto.builder()
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .birth(request.getBirth())
                .phone(request.getPhone())
                .build();

        guestRepository.save(guestDto.toEntity());

        mailComponents.sendMail(guestDto.getEmail(), EMAIL_SUBJECT, EMAIL_TEXT + guestDto.getEmailAuthKey() + "'> 인증 가입 완료 </a></div>");

        return guestDto;

    }

    /**
     * 로그인을 요청한 유저에게 Access Token 과 Refresh Token 을 발급합니다.
     * @param request 유저의 email, password
     * @return Access Token, Refresh Token
     */
    public ResponseTokenDto logIn(LogIn request) {

        if (checkStatus(request)) {
            throw new GuestException(ExceptionMessage.NOT_AUTH_EMAIL);
        }

        GuestDto guestDto = authenticate(request);

        String refToken = this.jwtTokenProvider.generateRefToken(guestDto.getName());

        RefreshToken refTokenEntity = RefreshToken.createTokenEntity(guestDto.getEmail(), refToken);
        this.refreshTokenRepository.save(refTokenEntity);

        return ResponseTokenDto.builder()
            .accessToken(this.jwtTokenProvider.generateAccessToken(guestDto.getName(), guestDto.getStatus()))
            .refreshToken(refToken)
            .build();
    }

    @Transactional(readOnly = true)
    public Guest findGuestByEmail(String email) {
        if (!guestRepository.existsByEmail(email)) {
            throw new GuestException(ExceptionMessage.NOT_EXIST_GUEST);
        }
        return guestRepository.findByEmail(email).get();
    }

    /**
     * 이메일 인증 메서드
     * @param uuid 인증에 사용 된 authKey
     * @return db에 저장된 유저의 key 와 일치하면 true 를 반환
     */
    public boolean emailAuth(String uuid) {

        Optional<Guest> guestOpt = this.guestRepository.findByEmailAuthKey(uuid);

        if (uuid != guestOpt.get().getEmailAuthKey() && !guestOpt.isPresent()) {
            return false;
        }

        Guest guest = guestOpt.get();
        guest.changeStatus(GuestStatus.ACTIVE);
        guestRepository.save(guest);

        return true;
    }

    /**
     * 요청된 유저의 정보와 DB 에 저장된 정보가 일치하는지 확인
     * @param request 유저 정보 (email, password)
     * @return GuestDto (email, password, name)
     */
    private GuestDto authenticate(LogIn request) {
        Guest guest = this.guestRepository.findByEmail(request.getEmail())
                                        .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

        if (!this.passwordEncoder.matches(request.getPassword(), guest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return GuestDto.builder()
            .email(guest.getEmail())
            .password(guest.getPassword())
            .name(guest.getName())
            .build();
    }

    /**
     * 이메일 인증이 완료된 회원인지 확인합니다.
     * @param request 유저의 이메일과 패스워드
     * @return boolean
     */
    public boolean checkStatus(LogIn request) {
        Guest guest = guestRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new GuestException(ExceptionMessage.NOT_EXIST_GUEST));

        if (guest.getStatus() == GuestStatus.NOT_AUTH) {
            throw new GuestException(ExceptionMessage.NOT_AUTH_EMAIL);
        }

        return false;
    }

    /**
     * 로그아웃 메서드
     * 로그아웃 시 db 에 저장된 refresh token 이 삭제됩니다.
     * @param request 유저의 이메일, refresh token
     */
    public String logOut(LogOut request) {
        RefreshToken refToken = refreshTokenRepository.findByGuestEmail(request.getEmail())
                                    .orElseThrow(() -> new GuestException(ExceptionMessage.NOT_EXIST_GUEST));

        refreshTokenRepository.delete(refToken);

        return request.getEmail();
    }
}