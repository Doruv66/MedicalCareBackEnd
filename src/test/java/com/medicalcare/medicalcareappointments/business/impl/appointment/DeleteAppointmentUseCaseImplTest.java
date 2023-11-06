package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
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

    @InjectMocks
    private DeleteAppointmentUseCaseImpl deleteAppointmentUseCase;

    @Test
    void deleteAppointment_shouldDeleteAppointment() {
        //Arrange
        long appointmentId = 1L;

        when(appointmentRepositoryMock.findById(appointmentId)).thenReturn(Optional.ofNullable(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Confirmed)
                .user(AccountUtilClass.createUserEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime())).build()));

        //Act
        deleteAppointmentUseCase.deleteAppointment(appointmentId);


        //Assert
        verify(appointmentRepositoryMock, times(1)).findById(appointmentId);

        verify(appointmentRepositoryMock, times(1)).deleteById(appointmentId);
    }

    @Test
    void deleteAppointment_shouldThrowNotFoundException() {
        //Arrange
        long appointmentId = 2L;

        when(appointmentRepositoryMock.findById(appointmentId)).thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(NotFoundAppointmentException.class, () -> deleteAppointmentUseCase.deleteAppointment(appointmentId));

        verify(appointmentRepositoryMock, times(1)).findById(appointmentId);

        verify(appointmentRepositoryMock, never()).deleteById(appointmentId);
    }

}