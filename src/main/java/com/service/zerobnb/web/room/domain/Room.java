package com.service.zerobnb.web.room.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
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
}
