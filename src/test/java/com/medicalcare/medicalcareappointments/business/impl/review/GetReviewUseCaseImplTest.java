package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.review.GetReviewUseCaseImpl;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
                .date(new Date(2011, 11, 11))
                .doctorId(1L)
                .rating(5)
                .userId(2L)
                .build();
        when(reviewRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(reviewEntity));

        //Act
        Review actualResult = getReviewUseCase.getReview(1L).get();

        //Assert
        Review expectedResult = Review.builder()
                .reviewId(1L)
                .comment("nice appointment")
                .doctorId(1L)
                .date(new Date(2011, 11, 11))
                .rating(5)
                .userId(2L)
                .build();

        assertEquals(actualResult, expectedResult);
    }
}