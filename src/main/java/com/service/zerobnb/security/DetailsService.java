package com.service.zerobnb.security;

import com.service.zerobnb.web.guest.UserDetailsImpl;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DetailsService implements UserDetailsService {

    private final GuestRepository guestRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Guest guest = this.guestRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(email + "이용자 정보를 찾을 수 없습니다."));

        return new UserDetailsImpl(guest);
    }
}
