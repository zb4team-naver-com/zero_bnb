package com.service.zerobnb.web.room.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomInput {
    @Lob
    private String basicOption;

    private long cost;

    @Lob
    private String description;

    private String name;

    private int discount;

    private int standardPeople;

    private int maxPeople;

    private int count;

    private boolean smoke;

    private List<RoomImageInput> roomImages;

    private Long accommodationId;
}
