package com.service.zerobnb.web.room.dto;

import com.service.zerobnb.web.accommodation.dto.AccommodationForGuestDto;
import com.service.zerobnb.web.accommodation.dto.AccommodationForHostDto;
import com.service.zerobnb.web.room.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {
    private Long id;
    private int standardPeople;
    private int maxPeople;
    private String name;
    private boolean smoke;
    private long cost;
    private int count;
    private int discount;
    private String description;
    private String basicOption;
    private AccommodationForGuestDto accommodationForGuestDto;
    private boolean isDelete;

    public static RoomDto from(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .standardPeople(room.getStandardPeople())
                .maxPeople(room.getMaxPeople())
                .name(room.getName())
                .smoke(room.isSmoke())
                .cost(room.getCost())
                .discount(room.getDiscount())
                .description(room.getDescription())
                .basicOption(room.getBasicOption())
                .isDelete(room.isDelete())
                .accommodationForGuestDto(AccommodationForGuestDto.from(room.getAccommodation()))
                .build();
    }
}
