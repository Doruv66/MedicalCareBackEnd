package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewResponse;
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
class UpdateReviewUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepositoryMock;

    @InjectMocks
    private UpdateReviewUseCaseImpl updateReviewUseCase;

    @Test
    public void updateReview_shouldUpdateReview() {
        // Arrange
        long id = 1L;
        UpdateReviewRequest request = new UpdateReviewRequest(5, "New comment", 1L, 2L);
        ReviewEntity existingReview = ReviewEntity.builder().reviewId(1L).comment("nice appointment").doctorId(1L).rating(5).userId(2L).build();

        when(reviewRepositoryMock.findById(id)).thenReturn(Optional.of(existingReview));

        // Act
        UpdateReviewResponse response = updateReviewUseCase.updateReview(request, id);

        // Assert
        assertEquals(id, response.getId());
        assertEquals(request.getComment(), existingReview.getComment());
        assertEquals(request.getRating(), existingReview.getRating());
        assertEquals(request.getDoctorId(), existingReview.getDoctorId());
        assertEquals(request.getUserId(), existingReview.getUserId());
        verify(reviewRepositoryMock, times(1)).findById(id);
        verify(reviewRepositoryMock, times(1)).save(existingReview);
    }

    @Test
    public void updateReview_shouldThrowNotFoundException() {
        // Arrange
        long id = 1L;
        UpdateReviewRequest request = new UpdateReviewRequest(5, "nice appointment", 1L, 2L);

        when(reviewRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundReviewException.class, () -> {
            updateReviewUseCase.updateReview(request, id);
        });

        verify(reviewRepositoryMock, times(1)).findById(id);
        verify(reviewRepositoryMock, never()).save(any());
    }
}