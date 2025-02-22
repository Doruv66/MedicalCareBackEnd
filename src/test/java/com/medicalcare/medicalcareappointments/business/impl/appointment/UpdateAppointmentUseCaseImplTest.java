package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentResponse;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
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
    void updateAppointment_shouldUpdateAppoointment() {
        // Arrange
        long id = 1L;
        UpdateAppointmentRequest request = UpdateAppointmentRequest.builder()
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .appointmentStatus(AppointmentStatus.BOOKED)
                .timeSlot(TimeSlot.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctor())
                        .build())
                .appointmentStatus(AppointmentStatus.BOOKED)
                .build();

        AppointmentEntity existingAppointment = AppointmentEntity.builder()
                .appointmentId(id)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .timeSlot(TimeSlotEntity.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctorEntity())
                        .build())
                .appointmentStatus(AppointmentStatus.BOOKED)
                .build();

        when(appointmentRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(existingAppointment));

        // Act
        UpdateAppointmentResponse response = updateAppointmentUseCase.updateAppointment(request, id);

        // Assert
        assertEquals(id, response.getId());
        assertEquals(request.getTimeSlot(), TimeSlotConverter.convert(existingAppointment.getTimeSlot()));
        assertEquals(request.getPatient(), AccountConverter.convert(existingAppointment.getPatient()));
        assertEquals(request.getDoctor(), AccountConverter.convert(existingAppointment.getDoctor()));
        assertEquals(request.getAppointmentStatus(), existingAppointment.getAppointmentStatus());
        verify(appointmentRepositoryMock, times(1)).findById(id);
        verify(appointmentRepositoryMock, times(1)).save(existingAppointment);
    }

    @Test
    void updateAppointment_shouldThrowNotFoundException() {
        // Arrange
        long id = 1L;
        UpdateAppointmentRequest request = UpdateAppointmentRequest.builder()
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .timeSlot(TimeSlot.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctor())
                        .build())
                .appointmentStatus(AppointmentStatus.BOOKED)
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