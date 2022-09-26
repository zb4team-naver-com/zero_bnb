package com.service.zerobnb.web.review.model;

import com.service.zerobnb.util.type.ReviewType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCategoryForm {

    private int score;
    private ReviewType reviewType;
}
