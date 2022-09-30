package com.service.zerobnb.web.accommodation.repository;

import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.host.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    boolean existsByAccommodationType(AccommodationType accommodationType);

    boolean existsByHost(Host host);
}
