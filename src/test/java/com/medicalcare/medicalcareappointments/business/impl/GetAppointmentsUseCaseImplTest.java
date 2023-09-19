package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.domain.Appointment;
import com.medicalcare.medicalcareappointments.domain.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.GetAppointmentsResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAppointmentsUseCaseImplTest {

    @Test
    void getAppointments_shouldReturnAllAppointmentsConverted() {
        AppointmentRepository appointmentRepositoryMock = mock(AppointmentRepository.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = "26-09-2019";
        Date actualDate =null;
        try {
            actualDate = formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

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

        GetAppointmentsUseCaseImpl getAppointmentsUseCase = new GetAppointmentsUseCaseImpl(appointmentRepositoryMock);
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