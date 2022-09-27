package com.service.zerobnb.web.review.service;

import com.service.zerobnb.util.LocationPosition;
import com.service.zerobnb.web.accommodation.domain.Accommodation;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.service.zerobnb.util.status.GuestStatus.ACTIVE;
import static com.service.zerobnb.util.type.AccommodationType.GUEST_HOUSE;
import static com.service.zerobnb.util.type.ReviewType.*;
import static com.service.zerobnb.util.type.TransportationType.VEHICLE;
import static com.service.zerobnb.web.error.message.ExceptionMessage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private ReviewCategoryRepository reviewCategoryRepository;

    @MockBean
    private GuestRepository guestRepository;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private ReservationService reservationService;


    @Test
    @DisplayName("리뷰를 작성하는 경우 - 리뷰 작성 성공")
    void registerReviewTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc123@gmail.com")
                .name("최웅")
                .birth("1999.11.11")
                .phone("010-1111-1111")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("경남 게스트 하우스")
                .locationPosition(new LocationPosition(25.5, 30.0))
                .address("경남 김해시")
                .description("자연의 풍경을 즐겨주세요.")
                .accommodationType(GUEST_HOUSE)
                .build();
        Room room = Room.builder()
                .id(1L)
                .standardPeople(3)
                .maxPeople(5)
                .description("경치가 최고!!")
                .name("경남 게스트 하우스")
                .basicOption("최고의 경치")
                .smoke(true)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .accommodation(accommodation)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));
        Reservation reservation = Reservation.builder()
                .guest(guest)
                .room(room)
                .checkInTime(LocalDateTime.of(2022,  10, 11, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022,  10, 14, 0, 0, 0))
                .days(3)
                .peopleCount(3)
                .transportationType(VEHICLE)
                .bookerName(guest.getName())
                .bookerPhone(guest.getPhone())
                .isReview(false)
                .build();
        given(reservationRepository.findByGuestIdAndRoomIdAndId(anyLong(), anyLong(), anyLong()))
                .willReturn(Optional.of(reservation));
        List<ReviewCategory> reviewCategoryList = Arrays.asList(ReviewCategory.builder()
                        .id(1L)
                        .score(5)
                        .reviewType(SERVICE_KINDNESS)
                        .build(),
                ReviewCategory.builder()
                        .id(2L)
                        .score(4)
                        .reviewType(ROOM_CLEANLINESS)
                        .build(),
                ReviewCategory.builder()
                        .id(3L)
                        .score(4)
                        .reviewType(FACILITY_CONVENIENCE)
                        .build(),
                ReviewCategory.builder()
                        .id(4L)
                        .score(3)
                        .reviewType(SUPPLIES_SATISFACTION)
                        .build()
        );
        Review review = Review.builder()
                .id(1L)
                .comment("정말 경치가 최고였습니다!!")
                .room(room)
                .guest(guest)
                .reviewCategoryList(reviewCategoryList)
                .build();
        given(reviewRepository.save(any()))
                .willReturn(review);
        given(reviewCategoryRepository.saveAll(any()))
                .willReturn(reviewCategoryList);

        // when
        List<ReviewCategoryForm> reviewCategoryForm = Arrays.asList(
                ReviewCategoryForm.builder()
                                .score(5)
                                .reviewType(SERVICE_KINDNESS)
                                .build(),
                ReviewCategoryForm.builder()
                                .score(4)
                                .reviewType(ROOM_CLEANLINESS)
                                .build(),
                ReviewCategoryForm.builder()
                                .score(4)
                                .reviewType(FACILITY_CONVENIENCE)
                                .build(),
                ReviewCategoryForm.builder()
                                .score(3)
                                .reviewType(SUPPLIES_SATISFACTION)
                                .build()
        );
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("정말 경치가 최고였습니다!!")
                .reviewCategoryList(reviewCategoryForm)
                .build();
        ReviewDto reviewDto = reviewService.registerReview(reviewForm);

        // then
        assertEquals("정말 경치가 최고였습니다!!", reviewDto.getComment());
        assertEquals(5, reviewDto.getReviewCategoryDtoList().get(0).getScore());
        assertEquals(SERVICE_KINDNESS, reviewDto.getReviewCategoryDtoList().get(0).getReviewType());
        assertEquals(4, reviewDto.getReviewCategoryDtoList().get(1).getScore());
        assertEquals(ROOM_CLEANLINESS, reviewDto.getReviewCategoryDtoList().get(1).getReviewType());
        assertEquals(4, reviewDto.getReviewCategoryDtoList().get(2).getScore());
        assertEquals(FACILITY_CONVENIENCE, reviewDto.getReviewCategoryDtoList().get(2).getReviewType());
        assertEquals(3, reviewDto.getReviewCategoryDtoList().get(3).getScore());
        assertEquals(SUPPLIES_SATISFACTION, reviewDto.getReviewCategoryDtoList().get(3).getReviewType());
    }
    
    @Test
    @DisplayName("게스트가 존재하지 않는 경우 - 리뷰 작성 실패")
    void notExistGuestRegisterTest() {
        // given
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.empty());

        // when
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .build();
        GuestException exception = assertThrows(GuestException.class,
                () -> reviewService.registerReview(reviewForm));

        // then
        assertEquals(NOT_EXIST_GUEST.message(), exception.getMessage());
    }
    
    @Test
    @DisplayName("방이 존재하지 않는 경우 - 리뷰 작성 실패")
    void notExistRoomRegisterTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc123@gmail.com")
                .name("최웅")
                .birth("1999.11.11")
                .phone("010-1111-1111")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .build();
        RoomException exception = assertThrows(RoomException.class,
                () -> reviewService.registerReview(reviewForm));

        // then
        assertEquals(NOT_EXIST_ROOM.message(), exception.getMessage());
    }
    
    @Test
    @DisplayName("예약 내역이 존재하지 않는 경우 - 리뷰 작성 실패")
    void notExistReservationRegisterTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc123@gmail.com")
                .name("최웅")
                .birth("1999.11.11")
                .phone("010-1111-1111")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));
        Room room = Room.builder()
                .id(1L)
                .standardPeople(3)
                .maxPeople(5)
                .description("경치가 최고!!")
                .name("경남 게스트 하우스")
                .basicOption("최고의 경치")
                .smoke(true)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .accommodation(null)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));
        given(reservationRepository.findByGuestIdAndRoomIdAndId(anyLong(), anyLong(), anyLong()))
                .willReturn(Optional.empty());

        // when
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .build();
        ReservationException exception = assertThrows(ReservationException.class,
                () -> reviewService.registerReview(reviewForm));

        // then
        assertEquals(NOT_EXIST_RESERVATION.message(), exception.getMessage());
    }
    
    @Test
    @DisplayName("이미 예약에 대한 리뷰를 작성한 경우 - 리뷰 작성 실패")
    void alreadyExistReviewTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc123@gmail.com")
                .name("최웅")
                .birth("1999.11.11")
                .phone("010-1111-1111")
                .status(ACTIVE)
                .build();
        given(guestRepository.findByEmail(anyString()))
                .willReturn(Optional.of(guest));
        Room room = Room.builder()
                .id(1L)
                .standardPeople(3)
                .maxPeople(5)
                .description("경치가 최고!!")
                .name("경남 게스트 하우스")
                .basicOption("최고의 경치")
                .smoke(true)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .accommodation(null)
                .build();
        given(roomRepository.findById(anyLong()))
                .willReturn(Optional.of(room));
        Reservation reservation = Reservation.builder()
                .guest(guest)
                .room(room)
                .checkInTime(LocalDateTime.of(2022,  10, 11, 0, 0, 0))
                .checkOutTime(LocalDateTime.of(2022,  10, 14, 0, 0, 0))
                .days(3)
                .peopleCount(3)
                .transportationType(VEHICLE)
                .bookerName(guest.getName())
                .bookerPhone(guest.getPhone())
                .isReview(true)
                .build();
        given(reservationRepository.findByGuestIdAndRoomIdAndId(anyLong(), anyLong(), anyLong()))
                .willReturn(Optional.of(reservation));

        // when
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .build();
        ReviewException exception = assertThrows(ReviewException.class,
                () -> reviewService.registerReview(reviewForm));

        // then
        assertEquals(ALREADY_EXIST_REVIEW.message(), exception.getMessage());
    }
    
    @Test
    @DisplayName("작성한 리뷰 내역을 확인할 경우 - 리뷰 확인 성공")
    void myReviewTest() {
        // given
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("경남 게스트 하우스")
                .locationPosition(new LocationPosition(25.5, 30.0))
                .address("경남 김해시")
                .description("자연의 풍경을 즐겨주세요.")
                .accommodationType(GUEST_HOUSE)
                .build();
        Room room = Room.builder()
                .id(1L)
                .standardPeople(3)
                .maxPeople(5)
                .description("경치가 최고!!")
                .name("경남 게스트 하우스")
                .basicOption("최고의 경치")
                .smoke(true)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .accommodation(accommodation)
                .build();
        ReviewCategory reviewCategory = ReviewCategory.builder()
                .id(1L)
                .score(5)
                .reviewType(SERVICE_KINDNESS)
                .build();
        List<Review> review = Arrays.asList(Review.builder()
                .id(1L)
                .comment("풍경이 너무 좋았어요!")
                .room(room)
                .reviewCategoryList(Arrays.asList(reviewCategory))
                .build(),
                Review.builder()
                        .id(2L)
                        .comment("좋은 추억 쌓고 가요!")
                        .room(room)
                        .reviewCategoryList(Arrays.asList(reviewCategory))
                        .build()
        );
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc123@gmail.com")
                .name("최웅")
                .birth("1999.11.11")
                .phone("010-1111-1111")
                .status(ACTIVE)
                .build();
        guest.setReviewList(review);
        given(reservationService.existGuestException(anyString()))
                .willReturn(guest);

        // when
        List<ReviewDto> reviewDtoList = reviewService.myReview(1L, "abc123@gmail.com");

        // then
        assertEquals("풍경이 너무 좋았어요!", reviewDtoList.get(0).getComment());
        assertEquals("좋은 추억 쌓고 가요!", reviewDtoList.get(1).getComment());
        assertEquals(5, reviewDtoList.get(0).getReviewCategoryDtoList().get(0).getScore());
        assertEquals(5, reviewDtoList.get(1).getReviewCategoryDtoList().get(0).getScore());
    }
    
    @Test
    @DisplayName("잘못된 접근일 경우(guestId != guest.getId()) - 리뷰 확인 실패")
    void notValidInputMyTest() {
        // given
        Guest guest = Guest.builder()
                .id(1L)
                .email("abc123@gmail.com")
                .name("최웅")
                .birth("1999.11.11")
                .phone("010-1111-1111")
                .status(ACTIVE)
                .build();
        given(reservationService.existGuestException(anyString()))
                .willReturn(guest);

        // when
        ValidationException exception = assertThrows(ValidationException.class,
                () -> reviewService.myReview(2L, "abc123@gmail.com"));

        // then
        assertEquals(NOT_VALID_INPUT.message(), exception.getMessage());
    }
    
    @Test
    @DisplayName("작성한 리뷰를 수정할 경우 - 리뷰 수정 성공")
    void updateReviewTest() {
        // given
        given(guestRepository.existsByEmail(anyString()))
                .willReturn(true);
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("경남 게스트 하우스")
                .locationPosition(new LocationPosition(25.5, 30.0))
                .address("경남 김해시")
                .description("자연의 풍경을 즐겨주세요.")
                .accommodationType(GUEST_HOUSE)
                .build();
        Room room = Room.builder()
                .id(1L)
                .standardPeople(3)
                .maxPeople(5)
                .description("경치가 최고!!")
                .name("경남 게스트 하우스")
                .basicOption("최고의 경치")
                .smoke(true)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .accommodation(accommodation)
                .build();
        ReviewCategory reviewCategory = ReviewCategory.builder()
                .id(1L)
                .score(5)
                .reviewType(SERVICE_KINDNESS)
                .build();
        Review review = Review.builder()
                .id(1L)
                .comment("풍경이 너무 좋았어요!")
                .room(room)
                .reviewCategoryList(Arrays.asList(reviewCategory))
                .build();
        given(reviewRepository.findById(anyLong()))
                .willReturn(Optional.of(review));

        // when
        ReviewCategoryForm reviewCategoryForm = ReviewCategoryForm.builder()
                .score(3)
                .reviewType(SERVICE_KINDNESS)
                .build();
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .reviewCategoryList(Arrays.asList(reviewCategoryForm))
                .build();
        ReviewDto reviewDto = reviewService.updateReview(1L, reviewForm);

        // then
        assertEquals("즐거운 여행되었습니다.", reviewDto.getComment());
        assertEquals(3, reviewDto.getReviewCategoryDtoList().get(0).getScore());
    }
    
    @Test
    @DisplayName("게스트가 존재하지 않는 경우 - 리뷰 수정 실패")
    void notExistGuestUpdateTest() {
        // given
        given(guestRepository.existsByEmail(anyString()))
                .willReturn(false);

        // when
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .build();
        GuestException exception = assertThrows(GuestException.class,
                () -> reviewService.updateReview(1L, reviewForm));

        // then
        assertEquals(NOT_EXIST_GUEST.message(), exception.getMessage());
    }
    
    @Test
    @DisplayName("리뷰가 존재하지 않는 경우 - 리뷰 수정 실패")
    void notExistReviewUpdateTest() {
        // given
        given(guestRepository.existsByEmail(anyString()))
                .willReturn(true);
        given(reviewRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .build();
        ReviewException exception = assertThrows(ReviewException.class,
                () -> reviewService.updateReview(1L, reviewForm));

        // then
        assertEquals(NOT_EXIST_REVIEW.message(), exception.getMessage());
    }

    @Test
    @DisplayName("리뷰 카테고리 존재하지 않는 경우 - 리뷰 수정 실패")
    void notExistReviewCategoryUpdateTest() {
        // given
        given(guestRepository.existsByEmail(anyString()))
                .willReturn(true);
        Accommodation accommodation = Accommodation.builder()
                .id(1L)
                .name("경남 게스트 하우스")
                .locationPosition(new LocationPosition(25.5, 30.0))
                .address("경남 김해시")
                .description("자연의 풍경을 즐겨주세요.")
                .accommodationType(GUEST_HOUSE)
                .build();
        Room room = Room.builder()
                .id(1L)
                .standardPeople(3)
                .maxPeople(5)
                .description("경치가 최고!!")
                .name("경남 게스트 하우스")
                .basicOption("최고의 경치")
                .smoke(true)
                .roomCount(5)
                .cost(100000)
                .discount(10)
                .accommodation(accommodation)
                .build();
        ReviewCategory reviewCategory = ReviewCategory.builder()
                .id(1L)
                .score(5)
                .reviewType(SERVICE_KINDNESS)
                .build();
        Review review = Review.builder()
                .id(1L)
                .comment("풍경이 너무 좋았어요!")
                .room(room)
                .reviewCategoryList(Arrays.asList(reviewCategory))
                .build();
        given(reviewRepository.findById(anyLong()))
                .willReturn(Optional.of(review));

        // when
        ReviewCategoryForm reviewCategoryForm = ReviewCategoryForm.builder()
                .score(3)
                .reviewType(FACILITY_CONVENIENCE)
                .build();
        ReviewForm reviewForm = ReviewForm.builder()
                .roomId(1L)
                .reservationId(1L)
                .email("abc123@gmail.com")
                .comment("즐거운 여행되었습니다.")
                .reviewCategoryList(Arrays.asList(reviewCategoryForm))
                .build();
        ReviewException exception = assertThrows(ReviewException.class,
                () -> reviewService.updateReview(1L, reviewForm));

        // then
        assertEquals(NOT_EXIST_REVIEW_CATEGORY.message(), exception.getMessage());
    }
    
    @Test
    @DisplayName("리뷰를 삭제하는 경우 - 리뷰 삭제 성공")
    void deleteReviewTest() {
        // given
        ReviewCategory reviewCategory = ReviewCategory.builder()
                .id(1L)
                .score(5)
                .reviewType(SERVICE_KINDNESS)
                .build();
        Review review = Review.builder()
                .id(1L)
                .comment("너무 즐거웠어요~~!")
                .reviewCategoryList(List.of(reviewCategory))
                .build();
        given(reviewRepository.findById(anyLong()))
                .willReturn(Optional.of(review));

        // when
        reviewService.deleteReview(1L);

        // then
    }
    
    @Test
    @DisplayName("리뷰가 존재하지 않는 경우 - 리뷰 삭제 실패")
    void notExistReviewDeleteTest() {
        // given
        given(reviewRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        // when
        ReviewException exception = assertThrows(ReviewException.class,
                () -> reviewService.deleteReview(1L));

        // then
        assertEquals(NOT_EXIST_REVIEW.message(), exception.getMessage());
    }
}