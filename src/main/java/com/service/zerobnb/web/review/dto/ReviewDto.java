package com.service.zerobnb.web.review.dto;

import com.service.zerobnb.web.review.domain.Review;
import com.service.zerobnb.web.room.dto.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private Long id;

    private String comment;

    private RoomDto roomDto;

    private List<ReviewCategoryDto> reviewCategoryDtoList;

    public static ReviewDto from(Review review) {
        List<ReviewCategoryDto> categoryDtoList = review.getReviewCategoryList().stream()
                .map(ReviewCategoryDto::from).collect(Collectors.toList());

        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .roomDto(RoomDto.from(review.getRoom()))
                .reviewCategoryDtoList(categoryDtoList)
                .build();
    }
}
