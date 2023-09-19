package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.domain.Review;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;

final class ReviewConverter {

    private ReviewConverter(){}

    public static Review convert(ReviewEntity review){
        return Review.builder()
                .reviewId(review.getReviewId())
                .comment(review.getComment())
                .rating(review.getRating())
                .userId(review.getUserId())
                .doctorId(review.getDoctorId())
                .build();
    }
}
