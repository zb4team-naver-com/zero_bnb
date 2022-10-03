package com.service.zerobnb.web.review.service;

import com.service.zerobnb.web.error.model.*;
import com.service.zerobnb.web.guest.domain.Guest;
import com.service.zerobnb.web.guest.repository.GuestRepository;
import com.service.zerobnb.web.reservation.domain.Reservation;
import com.service.zerobnb.web.reservation.repository.ReservationRepository;
import com.service.zerobnb.web.reservation.service.ReservationService;
import com.service.zerobnb.web.review.domain.Review;
import com.service.zerobnb.web.review.domain.ReviewCategory;
import com.service.zerobnb.web.review.dto.ReviewDto;
import com.service.zerobnb.web.review.model.ReviewCategoryForm;
import com.service.zerobnb.web.review.model.ReviewForm;
import com.service.zerobnb.web.review.repository.ReviewCategoryRepository;
import com.service.zerobnb.web.review.repository.ReviewRepository;
import com.service.zerobnb.web.room.domain.Room;
import com.service.zerobnb.web.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.service.zerobnb.web.error.message.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final ReviewCategoryRepository reviewCategoryRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;


    @Transactional
    public ReviewDto registerReview(ReviewForm reviewForm) {
        Guest guest = guestRepository.findByEmail(reviewForm.getEmail())
                .orElseThrow(() -> new GuestException(NOT_EXIST_GUEST));

        Room room = roomRepository.findById(reviewForm.getRoomId())
                .orElseThrow(() -> new RoomException(NOT_EXIST_ROOM));

        Reservation reservation = reservationRepository.findByGuestIdAndRoomIdAndId(guest.getId(), room.getId(), reviewForm.getReservationId())
                .orElseThrow(() -> new ReservationException(NOT_EXIST_RESERVATION));

        if (reservation.isReview()) {
            throw new ReviewException(ALREADY_EXIST_REVIEW);
        }

        Review review = Review.from(reviewForm, guest, room);
        reviewRepository.save(review);
        reviewCategoryRepository.saveAll(reviewForm.getReviewCategoryList().stream()
                .map(reviewCategoryForm -> ReviewCategory.from(reviewCategoryForm, review)).collect(Collectors.toList()));

        reservation.setReview(true);
        reservationRepository.save(reservation);

        return ReviewDto.from(review);
    }

    public List<ReviewDto> myReview(Long guestId, String email) {
        Guest guest = reservationService.existGuestException(email);
        if (!guest.getId().equals(guestId)) {
            throw new ValidationException(NOT_VALID_INPUT);
        }

        return guest.getReviewList().stream()
                .map(ReviewDto::from).collect(Collectors.toList());
    }

    @Transactional
    public ReviewDto updateReview(Long reviewId, ReviewForm reviewForm) {
        boolean existGuest = guestRepository.existsByEmail(reviewForm.getEmail());
        if (!existGuest) {
            throw new GuestException(NOT_EXIST_GUEST);
        }

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(NOT_EXIST_REVIEW));

        review.setComment(reviewForm.getComment());

        for(ReviewCategoryForm categoryForm : reviewForm.getReviewCategoryList()) {
            ReviewCategory category = review.getReviewCategoryList().stream()
                    .filter(reviewCategory -> reviewCategory.getReviewType().equals(categoryForm.getReviewType()))
                    .findFirst().orElseThrow(() -> new ReviewException(NOT_EXIST_REVIEW_CATEGORY));
            category.setScore(categoryForm.getScore());
        }

        return ReviewDto.from(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(NOT_EXIST_REVIEW));
        List<ReviewCategory> reviewCategoryList = reviewCategoryRepository.findByReviewId(reviewId);

        reviewCategoryRepository.deleteAll(reviewCategoryList);
        reviewRepository.delete(review);
    }
}
