package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReviewUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepositoryMock;

    @InjectMocks
    private CreateReviewUseCaseImpl createReviewUseCase;

    @Test
    void createReview_shouldCreateNewReview() {
        //Arrange
        long id = 1;
        CreateReviewRequest request = CreateReviewRequest.builder()
                .rating(4)
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .comment("nice appointment")
                .build();
        ReviewEntity review = ReviewEntity.builder()
                .reviewId(id)
                .rating(4)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        when(reviewRepositoryMock.save(any(ReviewEntity.class))).thenReturn(review);

        //Act
        CreateReviewResponse actualResult = createReviewUseCase.createReview(request);

        //Assert
        CreateReviewResponse expectedResult = CreateReviewResponse.builder().id(id).build();

        assertEquals(actualResult, expectedResult);
        verify(reviewRepositoryMock).save(any(ReviewEntity.class));
    }

}