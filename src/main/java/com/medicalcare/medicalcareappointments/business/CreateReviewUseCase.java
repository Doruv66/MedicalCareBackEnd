package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.CreateReviewResponse;

public interface CreateReviewUseCase {
    CreateReviewResponse createReview(CreateReviewRequest request);
}
