package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteAppointmentUseCaseImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private DeleteAppointmentUseCaseImpl deleteAppointmentUseCase;

    @Test
    void deleteAppointment_shouldDeleteAppointment() {
        long appointmentId = 1L;

        when(appointmentRepositoryMock.findById(appointmentId)).thenReturn(Optional.ofNullable(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Confirmed).userId(2L).doctorId(1L).dateTime(new Date()).build()));

        deleteAppointmentUseCase.deleteAppointment(appointmentId);

        verify(appointmentRepositoryMock, times(1)).findById(appointmentId);

        verify(appointmentRepositoryMock, times(1)).deleteById(appointmentId);
    }

    @Test
    void deleteAppointment_shouldThrowNotFoundException() {
        long appointmentId = 2L;

        when(appointmentRepositoryMock.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundAppointmentException.class, () -> deleteAppointmentUseCase.deleteAppointment(appointmentId));

        verify(appointmentRepositoryMock, times(1)).findById(appointmentId);

        verify(appointmentRepositoryMock, never()).deleteById(appointmentId);
    }

}