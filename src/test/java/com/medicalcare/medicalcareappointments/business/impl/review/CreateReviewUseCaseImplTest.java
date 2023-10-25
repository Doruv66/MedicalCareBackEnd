package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
                .doctorId(3L)
                .date(new Date(2011, 11, 11))
                .userId(3L)
                .comment("nice appointment")
                .build();
        ReviewEntity review = ReviewEntity.builder()
                .reviewId(id)
                .comment("nice appointment")
                .date(new Date(2011, 11, 11))
                .rating(4)
                .userId(3L)
                .doctorId(3L)
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