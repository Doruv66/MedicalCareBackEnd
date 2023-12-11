package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
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
class DeleteAppointmentUseCaseImplTest {

    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @Mock
    private TimeSlotRepository timeSlotRepositoryMock;

    @InjectMocks
    private DeleteAppointmentUseCaseImpl deleteAppointmentUseCase;

    @Test
    void deleteAppointment_shouldDeleteAppointment() {
        // Arrange
        long appointmentId = 1L;
        AppointmentEntity appointmentEntity = createSampleAppointmentEntity();

        when(appointmentRepositoryMock.findById(appointmentId)).thenReturn(Optional.of(appointmentEntity));

        // Act
        deleteAppointmentUseCase.deleteAppointment(appointmentId);

        // Assert
        verify(appointmentRepositoryMock, times(1)).findById(appointmentId);
        verify(timeSlotRepositoryMock, times(1)).save(appointmentEntity.getTimeSlot());
        verify(appointmentRepositoryMock, times(1)).deleteById(appointmentId);
    }

    @Test
    void deleteAppointment_shouldThrowNotFoundException() {
        // Arrange
        long appointmentId = 2L;

        when(appointmentRepositoryMock.findById(appointmentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundAppointmentException.class, () -> deleteAppointmentUseCase.deleteAppointment(appointmentId));

        verify(appointmentRepositoryMock, times(1)).findById(appointmentId);
        verify(timeSlotRepositoryMock, never()).save(any(TimeSlotEntity.class));
        verify(appointmentRepositoryMock, never()).deleteById(appointmentId);
    }

    private AppointmentEntity createSampleAppointmentEntity() {
        TimeSlotEntity timeSlot = TimeSlotEntity.builder()
                .startTime(new Timestamp(new Date(2023 - 1900, 1, 1).getTime()))
                .endTime(new Timestamp(new Date(2023 - 1900, 1, 2).getTime()))
                .timeSlotType(TimeSlotType.AVAILABLE)
                .build();

        return AppointmentEntity.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.BOOKED)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .timeSlot(timeSlot)
                .build();
    }
}