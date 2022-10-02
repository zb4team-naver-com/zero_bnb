package com.service.zerobnb.web.review.repository;

import com.service.zerobnb.web.review.domain.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory, Long> {
    List<ReviewCategory> findByReviewId(Long reviewId);
}
