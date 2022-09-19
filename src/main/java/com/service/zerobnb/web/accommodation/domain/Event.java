package com.service.zerobnb.web.accommodation.domain;


import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.web.accommodation.model.EventInput;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@ToString(exclude = "accommodation")
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    private String description;

    public static Event from(EventInput eventInput, Accommodation accommodation) {
        return Event.builder()
                .accommodation(accommodation)
                .description(eventInput.getDescription())
                .build();
    }
}
