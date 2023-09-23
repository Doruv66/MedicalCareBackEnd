package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.review.Review;

import java.util.Optional;

public interface GetReviewUseCase {
    Optional<Review> getReview(long id);
}
