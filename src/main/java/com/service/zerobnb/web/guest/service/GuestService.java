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

    public GuestDto getGuestInfo(Long userId) {

        Guest guest = guestRepository.findById(userId)
            .orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));

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

    @Transactional
    public GuestDto update(Long id, GuestInput request) {
        Guest guest = guestRepository.findById(id)
                                    .orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));

        guestRepository.save(guest.from(request));

        return GuestDto.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .name(request.getName())
                    .birth(request.getBirth())
                    .phone(request.getPhone())
                    .profileImage(request.getProfileImage())
                    .build();
    }

    @Transactional
    public void withdraw(Long id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));

        boolean checkedRoom = accommodationRepository.existsByHost(guest.getHost());

        if (checkedRoom) {
            throw new GuestException(MUST_DELETE_ACCOMMODATION);
        }

        guest.changeStatus(GuestStatus.WITHDRAW);
        guestRepository.save(guest);
    }
}
