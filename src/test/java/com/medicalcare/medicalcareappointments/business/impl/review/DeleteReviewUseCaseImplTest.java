package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
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
class DeleteReviewUseCaseImplTest {
    @Mock
    private ReviewRepository reviewRepositoryMock;

    @InjectMocks
    private DeleteReviewUseCaseImpl deleteReviewUseCase;

    @Test
    void deleteReview_shouldDeleteTheReview() {
        //Arrange
        long reviewId = 1L;

        when(reviewRepositoryMock.findById(reviewId)).thenReturn(Optional.ofNullable(ReviewEntity.builder().comment("bad appointment")
                .user(AccountUtilClass.createUserEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(1).build()));

        //Act
        deleteReviewUseCase.deleteReview(reviewId);

        //Assert
        verify(reviewRepositoryMock, times(1)).findById(reviewId);

        verify(reviewRepositoryMock, times(1)).deleteById(reviewId);
    }

    @Test
    void deleteReview_shouldThrowNotFoundException() {
        //Arrange
        long reviewId = 2L;

        when(reviewRepositoryMock.findById(reviewId)).thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(NotFoundReviewException.class, () -> deleteReviewUseCase.deleteReview(reviewId));

        verify(reviewRepositoryMock, times(1)).findById(reviewId);

        verify(reviewRepositoryMock, never()).deleteById(reviewId);
    }
}