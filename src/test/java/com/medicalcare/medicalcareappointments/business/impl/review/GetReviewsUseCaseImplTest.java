package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.review.GetReviewsResponse;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReviewsUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepositoryMock;

    @InjectMocks
    private GetReviewsUseCaseImpl getReviewsUseCase;

    @Test
    void getReviews_shouldReturnAllReviewsConverted() {
        //Arrange
        ReviewEntity review1Entity = ReviewEntity.builder()
                .comment("nice appointment")
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build();
        ReviewEntity review2Entity = ReviewEntity.builder()
                .comment("nice appointment")
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build();

        when(reviewRepositoryMock.findAll())
                .thenReturn(List.of(review1Entity, review2Entity));

        //Act
        GetReviewsResponse actualResult = getReviewsUseCase.getReviews();


        //Assert
        Review review1 = Review.builder()
                .comment("nice appointment")
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build();
        Review review2 = Review.builder()
                .comment("nice appointment")
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build();

        GetReviewsResponse expectedResult = GetReviewsResponse.builder()
                .reviews(List.of(review1, review2))
                .build();

        assertEquals(actualResult, expectedResult);
    }
}