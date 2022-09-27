package com.service.zerobnb.web.review.model;

import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewForm {

    private Long reservationId;
    private Long roomId;
    private String email;

    @Lob
    @NotEmpty(message = "리뷰 내용은 빈 값이 올 수 없습니다.")
    private String comment;
    private List<ReviewCategoryForm> reviewCategoryList;
}
