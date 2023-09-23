package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;

public interface CreateReviewUseCase {
    CreateReviewResponse createReview(CreateReviewRequest request);
}
