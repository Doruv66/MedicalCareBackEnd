package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.UpdateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.UpdateReviewResponse;

public interface UpdateReviewUseCase {
    UpdateReviewResponse updateReview(UpdateReviewRequest request, long id);
}
