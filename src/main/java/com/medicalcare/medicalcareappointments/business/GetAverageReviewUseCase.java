package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.review.GetAverageReviewResponse;

public interface GetAverageReviewUseCase {
    GetAverageReviewResponse getAverageReview(long doctorId);
}
