package com.service.zerobnb.web.review.domain;


import com.service.zerobnb.util.type.ReviewType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@ToString(exclude = "review")
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_category_id")
    private Long id;

    private int score;

    @Enumerated(EnumType.ORDINAL)
    private ReviewType reviewType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
