package com.service.zerobnb.web.room.dto;

import com.service.zerobnb.web.accommodation.dto.AccommodationForGuestDto;
import com.service.zerobnb.web.room.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomInfoDto {

    private Long id;
    private int standardPeople;
    private int maxPeople;
    private String name;
    private boolean smoke;
    private long cost;
    private int discount;

    public static RoomInfoDto from(Room room) {
        return RoomInfoDto.builder()
                .id(room.getId())
                .standardPeople(room.getStandardPeople())
                .maxPeople(room.getMaxPeople())
                .name(room.getName())
                .smoke(room.isSmoke())
                .cost(room.getCost())
                .discount(room.getDiscount())
                .build();
    }
}
