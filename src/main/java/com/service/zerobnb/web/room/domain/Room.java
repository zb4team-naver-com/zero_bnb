package com.service.zerobnb.web.room.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.review.domain.Review;
import com.service.zerobnb.web.room.model.RoomInput;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@ToString(exclude = "accommodation")
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private int standardPeople;

    private int maxPeople;

    @Lob
    private String description;

    private String name;

    @Lob
    private String basicOption;

    private boolean smoke;

    private int roomCount;

    private long cost;

    private int discount;

    private boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "room")
    private List<RoomImage> roomImageList;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "room")
    private List<Review> reviewList;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservationList;

    public static Room from(RoomInput roomInput, Accommodation accommodation) {
        return Room.builder()
                .standardPeople(roomInput.getStandardPeople())
                .maxPeople(roomInput.getMaxPeople())
                .description(roomInput.getDescription())
                .name(roomInput.getName())
                .basicOption(roomInput.getBasicOption())
                .smoke(roomInput.isSmoke())
                .roomCount(roomInput.getCount())
                .cost(roomInput.getCost())
                .discount(roomInput.getDiscount())
                .isDelete(false)
                .accommodation(accommodation)
                .build();
    }

    public static Room updateByRoomInput(Room room, RoomInput roomInput) {
        room.setBasicOption(roomInput.getBasicOption());
        room.setCost(roomInput.getCost());
        room.setDescription(roomInput.getDescription());
        room.setName(roomInput.getName());
        room.setDiscount(roomInput.getDiscount());
        room.setStandardPeople(roomInput.getStandardPeople());
        room.setMaxPeople(roomInput.getMaxPeople());
        room.setRoomCount(roomInput.getCount());
        room.setSmoke(roomInput.isSmoke());
        return room;
    }
}
