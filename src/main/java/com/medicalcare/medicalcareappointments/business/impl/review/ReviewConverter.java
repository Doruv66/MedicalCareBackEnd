package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
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
                .user((User) AccountConverter.convert(review.getUser()))
                .doctor((Doctor) AccountConverter.convert(review.getDoctor()))
                .build();
    }
}
