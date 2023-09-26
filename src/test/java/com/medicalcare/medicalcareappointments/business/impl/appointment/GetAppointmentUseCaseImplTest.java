package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.appointment.GetAppointmentUseCaseImpl;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAppointmentUseCaseImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private GetAppointmentUseCaseImpl getAppointmentUseCase;

    @Test
    void getAppointment_shouldReturnConvertedAppointment() {
        //Arrange
        long id = 1L;
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentId(id)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(2L).
                doctorId(1L).
                dateTime(new Date(2011, 11, 11))
                .build();
        when(appointmentRepositoryMock.findById(id))
                .thenReturn(Optional.ofNullable(appointmentEntity));


        //Act
        Appointment actualResult = getAppointmentUseCase.getAppointment(id).get();


        //Assert
        Appointment expectedResult = Appointment.builder()
                .appointmentId(id)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(2L).
                doctorId(1L).
                dateTime(new Date(2011, 11, 11))
                .build();

        assertEquals(actualResult, expectedResult);
    }

}