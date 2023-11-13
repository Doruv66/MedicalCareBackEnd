package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.review.GetAverageReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAverageReviewUseCaseImplTest {

    @Mock
    private ReviewRepository reviewRepositoryMock;

    @InjectMocks
    private GetAverageReviewUseCaseImpl getAverageReviewUseCase;

    @Test
    void getAverageReview_shouldReturnTheAverageRatingForDoctor() {

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
                .rating(4)
                .build();

        when(reviewRepositoryMock.getAverageRatingForDoctor(4L))
                .thenReturn((double) (review1Entity.getRating() + review2Entity.getRating()) / 2);

        //Act
        GetAverageReviewResponse actualResult = getAverageReviewUseCase.getAverageReview(4L);

        //Assert
        GetAverageReviewResponse expectedResult = GetAverageReviewResponse.builder().averageReview(4.5).build();
        assertEquals(actualResult, expectedResult);
        verify(reviewRepositoryMock).getAverageRatingForDoctor(4L);

    }

    @Test
    void getAverageReview_shouldReturnNullWhenNoReviewsExist() {
        // Arrange
        when(reviewRepositoryMock.getAverageRatingForDoctor(4L)).thenReturn(null);

        // Act
        GetAverageReviewResponse actualResult = getAverageReviewUseCase.getAverageReview(4L);

        // Assert
        assertNull(actualResult);
        verify(reviewRepositoryMock).getAverageRatingForDoctor(4L);
    }
}