package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentResponse;
import com.medicalcare.medicalcareappointments.exception.InvalidAppointmentException;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAppointmentUseCaseImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private UpdateAppointmentUseCaseImpl updateAppointmentUseCase;

    @Test
    public void updateAppointment_shouldUpdateAppoointment() {
        // Arrange
        long id = 1L;
        UpdateAppointmentRequest request = UpdateAppointmentRequest.builder()
                .user(AccountUtilClass.createUser())
                .doctor(AccountUtilClass.createDoctor())
                .appointmentStatus(AppointmentStatus.Pending)
                .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .appointmentStatus(com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus.Cancelled)
                .build();

        AppointmentEntity existingAppointment = AppointmentEntity.builder()
                .appointmentId(id)
                .user(AccountUtilClass.createUserEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .appointmentStatus(com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus.Pending)
                .build();

        when(appointmentRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(existingAppointment));

        // Act
        UpdateAppointmentResponse response = updateAppointmentUseCase.updateAppointment(request, id);

        // Assert
        assertEquals(id, response.getId());
        assertEquals(request.getDateTime(), existingAppointment.getDateTime());
        assertEquals(request.getUser(), AccountConverter.convert(existingAppointment.getUser()));
        assertEquals(request.getDoctor(), AccountConverter.convert(existingAppointment.getDoctor()));
        assertEquals(request.getAppointmentStatus(), existingAppointment.getAppointmentStatus());
        verify(appointmentRepositoryMock, times(1)).findById(id);
        verify(appointmentRepositoryMock, times(1)).save(existingAppointment);
    }

    @Test
    public void updateAppointment_shouldThrowNotFoundException() {
        // Arrange
        long id = 1L;
        UpdateAppointmentRequest request = UpdateAppointmentRequest.builder()
                .user(AccountUtilClass.createUser())
                .doctor(AccountUtilClass.createDoctor())
                .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .appointmentStatus(AppointmentStatus.Confirmed)
                .build();

        when(appointmentRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundAppointmentException.class, () -> {
            updateAppointmentUseCase.updateAppointment(request, id);
        });

        verify(appointmentRepositoryMock, times(1)).findById(id);
        verify(appointmentRepositoryMock, never()).save(any());
    }
}