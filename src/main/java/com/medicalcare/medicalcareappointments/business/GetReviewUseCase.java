package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.review.GetReviewResponse;


public interface GetReviewUseCase {
    GetReviewResponse getReview(long id);
}
