package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewConverterTest {
    @Test
    void convertReview_shouldConvertReview() {
        //Arrange
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reviewId(1L)
                .comment("Nice Review")
                .doctorId(2L)
                .userId(4L)
                .rating(5)
                .build();

        //Act
        Review review = ReviewConverter.convert(reviewEntity);

        //Assert
        assertEquals(reviewEntity.getReviewId(), review.getReviewId());
        assertEquals(reviewEntity.getComment(), review.getComment());
        assertEquals(reviewEntity.getDoctorId(), review.getDoctorId());
        assertEquals(reviewEntity.getUserId(), review.getUserId());
        assertEquals(reviewEntity.getRating(), review.getRating());
    }
}