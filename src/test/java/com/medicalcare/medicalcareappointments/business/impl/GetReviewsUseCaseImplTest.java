package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.domain.GetReviewsResponse;
import com.medicalcare.medicalcareappointments.domain.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        ReviewEntity review1Entity = ReviewEntity.builder()
                .comment("nice appointment")
                .doctorId(1L)
                .rating(5)
                .userId(2L)
                .build();
        ReviewEntity review2Entity = ReviewEntity.builder()
                .comment("nice appointment")
                .doctorId(1L)
                .rating(5)
                .userId(2L)
                .build();

        when(reviewRepositoryMock.findAll())
                .thenReturn(List.of(review1Entity, review2Entity));

        GetReviewsResponse actualResult = getReviewsUseCase.getReviews();

        Review review1 = Review.builder()
                .comment("nice appointment")
                .doctorId(1L)
                .rating(5)
                .userId(2L)
                .build();
        Review review2 = Review.builder()
                .comment("nice appointment")
                .doctorId(1L)
                .rating(5)
                .userId(2L)
                .build();

        GetReviewsResponse expectedResult = GetReviewsResponse.builder()
                .reviews(List.of(review1, review2))
                .build();

        assertEquals(actualResult, expectedResult);
    }
}