package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewResponse;

public interface UpdateReviewUseCase {
    UpdateReviewResponse updateReview(UpdateReviewRequest request, long id);
}
