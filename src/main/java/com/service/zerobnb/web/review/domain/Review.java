package com.service.zerobnb.web.review.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.room.domain.Room;
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
public class Review extends BaseTimeEntity {
    @Id
    @Column(name = "review_id")
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "review")
    private List<ReviewCategory> reviewCategoryList;
}
