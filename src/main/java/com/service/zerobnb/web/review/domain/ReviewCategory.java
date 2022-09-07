package com.service.zerobnb.web.review.domain;


import com.service.zerobnb.util.status.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCategory {
    @Id
    @Column(name = "review_category_id")
    private Long id;

    private int star;

    @Enumerated(EnumType.ORDINAL)
    private ReviewType reviewType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
