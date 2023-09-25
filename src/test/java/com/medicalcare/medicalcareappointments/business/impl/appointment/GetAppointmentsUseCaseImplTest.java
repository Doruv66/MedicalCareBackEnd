package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.appointment.GetAppointmentsUseCaseImpl;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentsResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAppointmentsUseCaseImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @InjectMocks
    private GetAppointmentsUseCaseImpl getAppointmentsUseCase;

    @Test
    void getAppointments_shouldReturnAllAppointmentsConverted() {
        Date actualDate = new Date(2010, 11 ,20);

        AppointmentEntity appointment1Entity = AppointmentEntity.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(2L)
                .doctorId(1L)
                .dateTime(actualDate)
                .build();
        AppointmentEntity appointment2Entity = AppointmentEntity.builder()
                .appointmentId(2L)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(3L)
                .doctorId(5L)
                .dateTime(actualDate)
                .build();
        when(appointmentRepositoryMock.findAll())
                .thenReturn(List.of(appointment1Entity, appointment2Entity));

        GetAppointmentsResponse actualResult = getAppointmentsUseCase.getAppointments();

        Appointment appointment1 = Appointment.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(2L)
                .doctorId(1L)
                .dateTime(actualDate)
                .build();
        Appointment appointment2 = Appointment.builder()
                .appointmentId(2L)
                .appointmentStatus(AppointmentStatus.Pending)
                .userId(3L)
                .doctorId(5L)
                .dateTime(actualDate)
                .build();

        GetAppointmentsResponse expectedResult = GetAppointmentsResponse.builder()
                .appointments(List.of(appointment1,appointment2))
                .build();

        assertEquals(actualResult, expectedResult);

        verify(appointmentRepositoryMock).findAll();
    }
}