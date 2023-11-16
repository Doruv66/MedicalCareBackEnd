package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentsResponse;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
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

        //Arrange
        AppointmentEntity appointment1Entity = AppointmentEntity.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.BOOKED)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .timeSlot(TimeSlotEntity.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctorEntity())
                        .build())
                .build();
        AppointmentEntity appointment2Entity = AppointmentEntity.builder()
                .appointmentId(2L)
                .appointmentStatus(AppointmentStatus.BOOKED)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .timeSlot(TimeSlotEntity.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctorEntity())
                        .build())
                .build();
        when(appointmentRepositoryMock.findAll())
                .thenReturn(List.of(appointment1Entity, appointment2Entity));

        //Act
        GetAppointmentsResponse actualResult = getAppointmentsUseCase.getAppointments();

        //Assert
        Appointment appointment1 = Appointment.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.BOOKED)
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .timeSlot(TimeSlot.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctor())
                        .build())
                .build();
        Appointment appointment2 = Appointment.builder()
                .appointmentId(2L)
                .appointmentStatus(AppointmentStatus.BOOKED)
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .timeSlot(TimeSlot.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctor())
                        .build())
                .build();

        GetAppointmentsResponse expectedResult = GetAppointmentsResponse.builder()
                .appointments(List.of(appointment1,appointment2))
                .build();

        assertEquals(actualResult, expectedResult);

        verify(appointmentRepositoryMock).findAll();
    }
}