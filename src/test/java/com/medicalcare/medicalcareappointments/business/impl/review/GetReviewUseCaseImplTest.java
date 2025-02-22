package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.review.GetReviewResponse;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReviewUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepositoryMock;

    @InjectMocks
    private GetReviewUseCaseImpl getReviewUseCase;

    @Test
    void getReview_shouldReturnConvertedReview() {
        //Arrange
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reviewId(1L)
                .comment("nice appointment")
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build();
        when(reviewRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(reviewEntity));

        //Act
        GetReviewResponse actualResult = getReviewUseCase.getReview(1L);

        //Assert
        GetReviewResponse expectedResult = GetReviewResponse.builder().review(Review.builder()
                .reviewId(1L)
                .comment("nice appointment")
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build()).build();

        assertEquals(actualResult, expectedResult);
    }
}