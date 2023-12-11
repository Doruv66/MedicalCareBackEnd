package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.appointment.AppointmentConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
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

    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private CreateReviewUseCaseImpl createReviewUseCase;

    @Test
    void createReview_shouldCreateNewReview() {
        // Arrange
        long id = 1;

        // Mocking necessary entities
        Appointment appointment = Appointment.builder()
                .appointmentId(id)
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .appointmentStatus(AppointmentStatus.BOOKED)
                .timeSlot(TimeSlot.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctor())
                        .build())
                .build();

        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentId(id)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .appointmentStatus(AppointmentStatus.BOOKED)
                .timeSlot(TimeSlotEntity.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctorEntity())
                        .build())
                .build();

        CreateReviewRequest request = buildSampleCreateReviewRequest(appointment);
        ReviewEntity review = buildSampleReviewEntity(id);

        when(reviewRepositoryMock.save(any(ReviewEntity.class))).thenReturn(review);
        when(appointmentRepositoryMock.save(any(AppointmentEntity.class))).thenReturn(appointmentEntity); // Mocking appointmentRepository.save()

        // Act
        CreateReviewResponse actualResult = createReviewUseCase.createReview(request);

        // Assert
        CreateReviewResponse expectedResult = CreateReviewResponse.builder().id(id).build();

        assertEquals(expectedResult, actualResult);
        verify(reviewRepositoryMock).save(any(ReviewEntity.class));
        verify(appointmentRepositoryMock).save(any(AppointmentEntity.class)); // Verify save method call
    }

    private CreateReviewRequest buildSampleCreateReviewRequest(Appointment appointment) {
        return CreateReviewRequest.builder()
                .rating(4)
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .comment("nice appointment")
                .appointment(appointment) // Set the mocked appointment object
                .build();
    }

    private ReviewEntity buildSampleReviewEntity(long id) {
        return ReviewEntity.builder()
                .reviewId(id)
                .rating(4)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .date(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
    }
}