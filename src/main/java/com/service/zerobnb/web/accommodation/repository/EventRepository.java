package com.service.zerobnb.web.accommodation.repository;

import com.service.zerobnb.web.accommodation.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
