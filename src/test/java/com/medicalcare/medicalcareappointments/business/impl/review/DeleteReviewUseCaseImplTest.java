package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.exception.NotFoundReviewException;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteReviewUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepositoryMock;

    @InjectMocks
    private DeleteReviewUseCaseImpl deleteReviewUseCase;

    @Test
    void deleteReview_shouldDeleteTheReview() {
        long reviewId = 1L;

        when(reviewRepositoryMock.findById(reviewId)).thenReturn(Optional.ofNullable(ReviewEntity.builder().comment("bad appointment").doctorId(1L).rating(1).userId(2L).build()));

        deleteReviewUseCase.deleteReview(reviewId);

        verify(reviewRepositoryMock, times(1)).findById(reviewId);

        verify(reviewRepositoryMock, times(1)).deleteById(reviewId);
    }

    @Test
    void deleteReview_shouldThrowNotFoundException() {
        long reviewId = 2L;

        when(reviewRepositoryMock.findById(reviewId)).thenReturn(Optional.empty());

        assertThrows(NotFoundReviewException.class, () -> deleteReviewUseCase.deleteReview(reviewId));

        verify(reviewRepositoryMock, times(1)).findById(reviewId);

        verify(reviewRepositoryMock, never()).deleteById(reviewId);
    }
}