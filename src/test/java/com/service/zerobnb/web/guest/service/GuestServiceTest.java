package com.service.zerobnb.web.guest.service;

import static com.service.zerobnb.util.status.GuestStatus.ACTIVE;
import static com.service.zerobnb.web.error.message.ExceptionMessage.NOT_EXIST_GUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.dto.GuestDto;
import com.service.zerobnb.web.guest.model.GuestInput;
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
class GuestServiceTest {

    @Autowired
    private GuestService guestService;

    @MockBean
    private GuestRepository guestRepository;

    @Test
    @DisplayName("특정 유저의 정보를 조회할 수 있다.")
    void getUserInfo() {
        // given
        Guest guest = Guest.builder()
            .id(11L)
            .email("abc@naver.com")
            .password("Password0!")
            .name("홍길동")
            .birth("1995.03.04")
            .phone("010-1111-2222")
            .status(ACTIVE)
            .profileImage("/image")
            .point(0L)
            .isHost(false)
            .build();
        given(guestRepository.findById(11L)).willReturn(Optional.of(guest));

        // when
        GuestDto guestDto = guestService.getGuestInfo(11L);

        // then
        assertEquals(guestDto.getEmail(), guest.getEmail());
        assertEquals(guestDto.getName(), guest.getName());
        assertEquals(guestDto.getPhone(), guest.getPhone());
    }

    @Test
    @DisplayName("db에 유저가 존재하지 않으면 exception 발생")
    void notExistGuest() {
        // given
        given(guestRepository.existsByEmail(anyString())).willReturn(false);
        // when
        GuestException exception = assertThrows(GuestException.class, () -> guestService.getGuestInfo(anyLong()));
        // then
        assertEquals(NOT_EXIST_GUEST.message(), exception.getMessage());
    }

    @Test
    @DisplayName("유저 정보를 수정할 수 있다.")
    void update() {
        // given
        Guest guest = Guest.builder()
            .id(11L)
            .email("abc@naver.com")
            .password("Password0!")
            .name("홍길동")
            .birth("1995.03.04")
            .phone("010-1111-2222")
            .status(ACTIVE)
            .profileImage("/image")
            .point(0L)
            .isHost(false)
            .build();
        given(guestRepository.findById(11L)).willReturn(Optional.of(guest));

        // when
        GuestInput guestInput = GuestInput.builder()
            .email("abc@naver.com")
            .password("Password0!")
            .name("바뀐이름")
            .birth("1995.03.04")
            .phone("010-1111-2222")
            .profileImage("/image")
            .build();
        GuestDto guestDto = guestService.update(11L, guestInput);

        // then
        assertEquals(guestInput.getName(), guestDto.getName());
    }
}