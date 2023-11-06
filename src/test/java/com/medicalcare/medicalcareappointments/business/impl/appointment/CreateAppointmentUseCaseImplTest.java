package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentResponse;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.Doc;
import java.sql.Timestamp;
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
                        .user(AccountUtilClass.createUser())
                        .doctor(AccountUtilClass.createDoctor())
                        .appointmentStatus(AppointmentStatus.Pending)
                        .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .build();
        AppointmentEntity appointment = AppointmentEntity.builder()
                        .appointmentId(id)
                        .user(AccountUtilClass.createUserEntity())
                        .doctor(AccountUtilClass.createDoctorEntity())
                        .appointmentStatus(AppointmentStatus.Pending)
                        .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
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