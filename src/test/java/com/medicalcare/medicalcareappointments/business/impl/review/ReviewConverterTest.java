package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReviewConverterTest {
    @Test
    void convertReview_shouldConvertReview() {
        //Arrange
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reviewId(1L)
                .comment("Nice Review")
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build();

        //Act
        Review review = ReviewConverter.convert(reviewEntity);

        //Assert
        assertEquals(reviewEntity.getReviewId(), review.getReviewId());
        assertEquals(reviewEntity.getDate(), review.getDate());
        assertEquals(reviewEntity.getComment(), review.getComment());
        assertEquals(AccountConverter.convert(reviewEntity.getDoctor()), review.getDoctor());
        assertEquals(AccountConverter.convert(reviewEntity.getPatient()), review.getPatient());
        assertEquals(reviewEntity.getRating(), review.getRating());
    }
}