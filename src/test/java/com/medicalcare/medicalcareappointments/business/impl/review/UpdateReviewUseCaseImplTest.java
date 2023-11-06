package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewResponse;
import com.medicalcare.medicalcareappointments.exception.NotFoundReviewException;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
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
        UpdateReviewRequest request = UpdateReviewRequest.builder().rating(4)
                .user(AccountUtilClass.createUser())
                .doctor(AccountUtilClass.createDoctor())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .comment("nice appointment")
                .build();
        ReviewEntity existingReview = ReviewEntity.builder().reviewId(1L).comment("nice appointment").rating(5)
                .user(AccountUtilClass.createUserEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();

        when(reviewRepositoryMock.findById(id)).thenReturn(Optional.of(existingReview));

        // Act
        UpdateReviewResponse response = updateReviewUseCase.updateReview(request, id);

        // Assert
        assertEquals(id, response.getId());
        assertEquals(request.getComment(), existingReview.getComment());
        assertEquals(request.getDate(), existingReview.getDate());
        assertEquals(request.getRating(), existingReview.getRating());
        assertEquals(request.getDoctor(), AccountConverter.convert(existingReview.getDoctor()));
        assertEquals(request.getUser(), AccountConverter.convert(existingReview.getUser()));
        verify(reviewRepositoryMock, times(1)).findById(id);
        verify(reviewRepositoryMock, times(1)).save(existingReview);
    }

    @Test
    public void updateReview_shouldThrowNotFoundException() {
        // Arrange
        long id = 1L;
        UpdateReviewRequest request = UpdateReviewRequest.builder().rating(4)
                .user(AccountUtilClass.createUser())
                .doctor(AccountUtilClass.createDoctor())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .comment("nice appointment")
                .build();
        when(reviewRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundReviewException.class, () -> {
            updateReviewUseCase.updateReview(request, id);
        });

        verify(reviewRepositoryMock, times(1)).findById(id);
        verify(reviewRepositoryMock, never()).save(any());
    }
}