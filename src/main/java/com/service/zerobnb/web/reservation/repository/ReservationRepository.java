package com.service.zerobnb.web.reservation.repository;

import com.service.zerobnb.web.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByGuestIdAndId(Long guestId, Long id);
    boolean existsByGuestIdAndCheckInTimeBetween(Long id, LocalDateTime checkInTime, LocalDateTime checkOutTime);
}
