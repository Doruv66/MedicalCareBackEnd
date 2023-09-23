package com.medicalcare.medicalcareappointments.business.impl;

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
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(2L).
                doctorId(1L).
                dateTime(new Date(2011, 11, 11))
                .build();
        when(appointmentRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(appointmentEntity));

        Appointment actualResult = getAppointmentUseCase.getAppointment(1L).get();

        Appointment expectedResult = Appointment.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(2L).
                doctorId(1L).
                dateTime(new Date(2011, 11, 11))
                .build();

        assertEquals(actualResult, expectedResult);
    }

}