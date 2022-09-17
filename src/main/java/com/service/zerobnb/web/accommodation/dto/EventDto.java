package com.service.zerobnb.web.accommodation.dto;

import com.service.zerobnb.web.accommodation.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private String description;

    public static EventDto from(Event event) {
        return EventDto.builder()
                .description(event.getDescription())
                .build();
    }
}
