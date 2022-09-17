package com.service.zerobnb.web.accommodation.repository;

import com.service.zerobnb.web.accommodation.domain.AccommodationImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationImageRepository extends JpaRepository<AccommodationImage, Long> {
}
