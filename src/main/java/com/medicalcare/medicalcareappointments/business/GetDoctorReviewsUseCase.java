package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.review.GetReviewsResponse;

public interface GetDoctorReviewsUseCase {
    GetReviewsResponse getDoctorReviews(long id);
}
