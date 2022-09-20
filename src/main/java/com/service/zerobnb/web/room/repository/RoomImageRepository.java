package com.service.zerobnb.web.room.repository;

import com.service.zerobnb.web.room.domain.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {
}
