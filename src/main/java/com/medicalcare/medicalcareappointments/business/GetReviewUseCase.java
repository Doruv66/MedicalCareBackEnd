package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.Account;
import com.medicalcare.medicalcareappointments.domain.Review;

import java.util.Optional;

public interface GetReviewUseCase {
    Optional<Review> getReview(long id);
}
