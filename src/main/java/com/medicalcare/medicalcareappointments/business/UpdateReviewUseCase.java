package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.UpdateReviewRequest;

public interface UpdateReviewUseCase {
    void updateReview(UpdateReviewRequest request, long id);
}
