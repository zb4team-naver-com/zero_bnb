package com.service.zerobnb.web.review.controller;

import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.error.model.ValidationException;
import com.service.zerobnb.web.review.dto.ReviewDto;
import com.service.zerobnb.web.review.model.ReviewForm;
import com.service.zerobnb.web.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.service.zerobnb.web.error.message.ExceptionMessage.NOT_LOGIN_STATUS;
import static com.service.zerobnb.web.error.message.ExceptionMessage.NOT_VALID_INPUT;

@Tag(name = "리뷰", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "게스트가 방문한 리뷰 등록", description = "리뷰 등록 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 성공"),
            @ApiResponse(responseCode = "500", description = "리뷰 등록 실패")
    })
    @PostMapping("/register")
    public ResponseEntity<ReviewDto> registerReview(@RequestBody @Valid ReviewForm reviewForm,
                                                    BindingResult bindingResult,
                                                    Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//        String email = principal.getName();
//
//        if (!reviewForm.getEmail().equals(email)) {
//            throw new ValidationException(NOT_VALID_INPUT);
//        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(NOT_VALID_INPUT);
        }

        return ResponseEntity.ok(reviewService.registerReview(reviewForm));
    }

    @Operation(summary = "게스트가 작성한 리뷰 정보", description = "리뷰 정보 반환 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 정보 반환 성공"),
            @ApiResponse(responseCode = "500", description = "리뷰 정보 반환 실패")
    })
    @GetMapping("/info/{guestId}")
    public ResponseEntity<List<ReviewDto>> myReview(@PathVariable Long guestId, Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//        String email = principal.getName();

        return ResponseEntity.ok(reviewService.myReview(guestId, "fdsfs@daum.net"));
    }

    @Operation(summary = "게스트가 작성한 리뷰 정보 수정", description = "리뷰 정보 수정 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 정보 수정 성공"),
            @ApiResponse(responseCode = "500", description = "리뷰 정보 수정 실패")
    })
    @PutMapping("update/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody @Valid ReviewForm reviewForm,
                                          @PathVariable Long reviewId,
                                          BindingResult bindingResult,
                                          Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//        String email = principal.getName();
//
//        if (!reviewForm.getEmail().equals(email)) {
//            throw new ValidationException(NOT_VALID_INPUT);
//        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(NOT_VALID_INPUT);
        }

        return ResponseEntity.ok(reviewService.updateReview(reviewId, reviewForm));
    }

    @Operation(summary = "게스트가 작성한 리뷰 정보 삭제", description = "리뷰 정보 삭제 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 정보 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "리뷰 정보 삭제 실패")
    })
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }

        reviewService.deleteReview(reviewId);

        return ResponseEntity.ok().build();
    }
}
