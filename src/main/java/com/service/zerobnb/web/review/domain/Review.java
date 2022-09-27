package com.service.zerobnb.web.review.domain;

import com.service.zerobnb.util.BaseTimeEntity;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.review.model.ReviewForm;
import com.service.zerobnb.web.room.domain.Room;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"room", "guest"})
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static Review from(ReviewForm reviewForm,Guest guest, Room room) {
        return Review.builder()
                .comment(reviewForm.getComment())
                .room(room)
                .guest(guest)
                .reviewCategoryList(reviewForm.getReviewCategoryList().stream()
                        .map(ReviewCategory::from).collect(Collectors.toList()))
                .build();
    }
}
