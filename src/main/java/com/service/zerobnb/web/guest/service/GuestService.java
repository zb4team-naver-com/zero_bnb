package com.service.zerobnb.web.guest.service;

import static com.service.zerobnb.web.error.message.ExceptionMessage.*;

import com.service.zerobnb.util.status.GuestStatus;
import com.service.zerobnb.web.accommodation.repository.AccommodationRepository;
import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.dto.GuestDto;
import com.service.zerobnb.web.guest.model.GuestInput;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final AccommodationRepository accommodationRepository;

    @Transactional(readOnly = true)
    public GuestDto getGuestInfo(Long userId) {

        Guest guest = guestRepository.findById(userId)
            .orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));

        return setDto(guest);
    }

    @Transactional
    public GuestDto update(Long id, GuestInput request) {
        Guest guest = guestRepository.findById(id)
                                    .orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));

        setEntity(guest, request);

        return setDto(request);
    }

    @Transactional
    public void withdraw(Long id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));

        boolean checkedRoom = accommodationRepository.existsByHost(guest.getHost());

        if (checkedRoom) {
            throw new GuestException(MUST_DELETE_ACCOMMODATION);
        }

        guest.changeStatus(GuestStatus.WITHDRAW);
    }

    private Guest setEntity(Guest guest, GuestInput input) {
        guest.setPassword(input.getPassword());
        guest.setName(input.getName());
        guest.setBirth(input.getBirth());
        guest.setPhone(input.getPhone());
        guest.setProfileImage(input.getProfileImage());

        return guest;
    }

    private GuestDto setDto(Guest guest) {
        return GuestDto.builder()
                    .email(guest.getEmail())
                    .name(guest.getName())
                    .phone(guest.getPhone())
                    .birth(guest.getBirth())
                    .profileImage(guest.getProfileImage())
                    .isHost(guest.isHost())
                    .point(guest.getPoint())
                    .build();
    }

    private GuestDto setDto(GuestInput input) {
        return GuestDto.builder()
                    .password(input.getPassword())
                    .name(input.getName())
                    .birth(input.getBirth())
                    .phone(input.getPhone())
                    .profileImage(input.getProfileImage())
                    .build();
    }
}
