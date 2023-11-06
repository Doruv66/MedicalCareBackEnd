package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.domain.review.GetAverageReviewResponse;
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
import java.util.List;

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
                .user(AccountUtilClass.createUserEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(5)
                .build();
        ReviewEntity review2Entity = ReviewEntity.builder()
                .comment("nice appointment")
                .user(AccountUtilClass.createUserEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .rating(4)
                .build();

        when(reviewRepositoryMock.findAll())
                .thenReturn(List.of(review1Entity, review2Entity));

        //Act
        GetAverageReviewResponse actualResult = getAverageReviewUseCase.getAverageReview(4L);

        //Assert
        GetAverageReviewResponse expectedResult = GetAverageReviewResponse.builder().averageReview(4.5).build();
        assertEquals(actualResult, expectedResult);
        verify(reviewRepositoryMock).findAll();

    }
}