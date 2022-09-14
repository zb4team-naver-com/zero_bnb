package com.service.zerobnb.web.guest.repository;

import com.service.zerobnb.web.guest.domain.Guest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Guest> findByEmailAuthKey(String emailAuthKey);
}
