package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentResponse;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAppointmentUseCaseImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private CreateAppointmentUseCaseImpl createAppointmentUseCase;

    @Test
    void createAppointment_shouldCreateNewAppointment() {
        //Arrange
        long id = 1;
        CreateAppointmentRequest request = CreateAppointmentRequest.builder()
                        .userId(1L)
                        .doctorId(2L)
                        .appointmentStatus(AppointmentStatus.Pending)
                        .dateTime(new Date(2011, 11, 11))
                        .build();
        AppointmentEntity appointment = AppointmentEntity.builder()
                        .appointmentId(id)
                        .userId(1L)
                        .doctorId(2L)
                        .appointmentStatus(AppointmentStatus.Pending)
                        .dateTime(new Date(2011, 11, 11))
                        .build();



        when(appointmentRepositoryMock.save(any(AppointmentEntity.class))).thenReturn(appointment);

        //Act
        CreateAppointmentResponse actualResult = createAppointmentUseCase.createAppointment(request);

        //Assert
        CreateAppointmentResponse expectedResult = CreateAppointmentResponse.builder().id(id).build();

        assertEquals(actualResult, expectedResult);
        verify(appointmentRepositoryMock).save(any(AppointmentEntity.class));
    }
}