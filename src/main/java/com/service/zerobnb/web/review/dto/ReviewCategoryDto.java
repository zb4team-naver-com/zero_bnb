package com.service.zerobnb.web.review.dto;

import com.service.zerobnb.util.type.ReviewType;
import com.service.zerobnb.web.review.domain.ReviewCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCategoryDto {

    private int score;
    private ReviewType reviewType;

    public static ReviewCategoryDto from(ReviewCategory reviewCategory) {
        return ReviewCategoryDto.builder()
                .score(reviewCategory.getScore())
                .reviewType(reviewCategory.getReviewType())
                .build();
    }
}
