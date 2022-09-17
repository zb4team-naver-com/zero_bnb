package com.service.zerobnb.web.guest.service;

import com.service.zerobnb.component.MailComponents;
import com.service.zerobnb.util.status.GuestStatus;
import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.guest.UserDetailsImpl;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.dto.GuestDto;
import com.service.zerobnb.web.guest.model.Auth;
import com.service.zerobnb.web.guest.model.Auth.SignUp;
import com.service.zerobnb.web.guest.repository.GuestRepository;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class GuestService implements UserDetailsService {

    private final GuestRepository guestRepository;

    private final PasswordEncoder passwordEncoder;
    private final MailComponents mailComponents;

    private final String EMAIL_SUBJECT = "zero bnb 가입 인증 메일입니다.";
    private final String EMAIL_TEXT = "<p> 아래 링크를 통해 가입을 완료하세요. </p> <div><a href='http://localhost:8000/signup/email-auth/";

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Guest guest = this.guestRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + "이용자 정보를 찾을 수 없습니다."));

        return new UserDetailsImpl(guest);
    }

    /**
     * 회원 가입을 요청한 유저를 db에 저장한 후 인증을 위한 이메일을 전송합니다.
     *
     * @param request controller 를 통해 받은 유저 정보
     * @return 성공적으로 저장이 완료된 유저 정보
     */
    public GuestDto register(SignUp request) {

        boolean exists = this.guestRepository.existsByEmail(request.getEmail());

        if (exists) {
            throw new RuntimeException("이미 사용중인 이메일입니다.");
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

    public GuestDto authenticate(Auth.LogIn user) {
        var guest = this.guestRepository.findByEmail(user.getEmail())
                                        .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

        if (!this.passwordEncoder.matches(user.getPassword(), guest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return null;
    }
}