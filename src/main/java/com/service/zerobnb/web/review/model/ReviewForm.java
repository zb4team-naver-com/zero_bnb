package com.service.zerobnb.web.review.model;

import lombok.*;

import javax.persistence.Lob;
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
    private String comment;
    private List<ReviewCategoryForm> reviewCategoryList;
}
