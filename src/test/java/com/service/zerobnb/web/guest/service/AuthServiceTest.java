package com.service.zerobnb.web.guest.service;

import static com.service.zerobnb.web.error.message.ExceptionMessage.ALREADY_EXIST_GUEST;
import static com.service.zerobnb.web.error.message.ExceptionMessage.NOT_AUTH_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.service.zerobnb.util.status.GuestStatus;
import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.model.Auth.LogIn;
import com.service.zerobnb.web.guest.model.Auth.SignUp;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private GuestRepository guestRepository;

    private final String EMAIL = "test@test.com";
    private final String PASSWORD = "password";
    private final String NAME = "홍길동";
    private final String BIRTH = "1995-03-12";
    private final String PHONE = "010-1111-2222";

    @Test
    @DisplayName("회원 가입 성공")
    void signUpSuccess() {
        // given
        SignUp guest = SignUp.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .name(NAME)
            .birth(BIRTH)
            .phone(PHONE)
            .build();

        // when
        authService.register(guest);

        //then
        verify(guestRepository, times(1)).existsByEmail(EMAIL);
    }

    @Test
    @DisplayName("DB에 동일한 이메일이 있는 경우 exception 발생")
    void signUpFailByDuplicatedEmail() {
        // given
        given(guestRepository.existsByEmail(EMAIL)).willReturn(true);

        // when
        SignUp guest = SignUp.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .name(NAME)
            .birth(BIRTH)
            .phone(PHONE)
            .build();
        GuestException exception = assertThrows(GuestException.class,
            () -> authService.register(guest));

        // then
        assertEquals(ALREADY_EXIST_GUEST.message(), exception.getMessage());
    }

    // TODO : 로그인 test
    @Test
    @DisplayName("로그인에 성공하면 토큰이 발급된다.")
    void logIn() {
        // given
        Guest guestE = Guest.builder()
                .id(1L)
                .email(EMAIL)
                .password(PASSWORD)
                .name(NAME)
                .birth(BIRTH)
                .phone(PHONE)
                .status(GuestStatus.ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guestE));

        // when
        LogIn guest = LogIn.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .build();
        authService.logIn(guest);

        // then
    }

    @Test
    @DisplayName("이메일 인증이 되지 않은 회원은 로그인에 실패한다.")
    void LogInFailByNotEmailAuth() {
        // given
        Guest guestE = Guest.builder()
            .id(1L)
            .email(EMAIL)
            .password(PASSWORD)
            .name(NAME)
            .birth(BIRTH)
            .phone(PHONE)
            .status(GuestStatus.NOT_AUTH)
            .build();
        given(guestRepository.findByEmail(anyString())).willReturn(Optional.of(guestE));

        // when
        LogIn logIn = LogIn.builder()
            .email(EMAIL)
            .password(PASSWORD)
            .build();
        GuestException exception = assertThrows(GuestException.class,
            () -> authService.logIn(logIn));

        // then
        assertEquals(NOT_AUTH_EMAIL.message(), exception.getMessage());
    }
}