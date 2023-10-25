package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;

final class ReviewConverter {

    private ReviewConverter(){}

    public static Review convert(ReviewEntity review){
        return Review.builder()
                .reviewId(review.getReviewId())
                .comment(review.getComment())
                .rating(review.getRating())
                .date(review.getDate())
                .userId(review.getUserId())
                .doctorId(review.getDoctorId())
                .build();
    }
}
