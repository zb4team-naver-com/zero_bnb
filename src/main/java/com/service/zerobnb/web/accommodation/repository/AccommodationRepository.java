package com.service.zerobnb.web.accommodation.repository;

import com.service.zerobnb.web.accommodation.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
