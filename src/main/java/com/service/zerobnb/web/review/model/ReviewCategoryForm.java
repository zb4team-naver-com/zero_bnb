package com.service.zerobnb.web.review.model;

import com.service.zerobnb.util.type.ReviewType;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCategoryForm {

    @NotEmpty(message = "리뷰 점수는 빈 값이 올 수 없습니다.")
    private int score;

    @NotEmpty(message = "리뷰 타입은 빈 값이 올 수 없습니다.")
    private ReviewType reviewType;
}
