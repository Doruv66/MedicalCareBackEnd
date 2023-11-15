package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentResponse;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAppointmentUseCaseImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private CreateAppointmentUseCaseImpl createAppointmentUseCase;

    @Test
    void createAppointment_shouldCreateNewAppointment() {
        //Arrange
        long id = 1;
        CreateAppointmentRequest request = CreateAppointmentRequest.builder()
                        .patient(AccountUtilClass.createPatient())
                        .doctor(AccountUtilClass.createDoctor())
                        .appointmentStatus(AppointmentStatus.CONFIRMED)
                        .timeSlot(TimeSlot.builder()
                                .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                                .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                                .doctor(AccountUtilClass.createDoctor())
                        .build()).build();
        AppointmentEntity appointment = AppointmentEntity.builder()
                        .appointmentId(id)
                        .patient(AccountUtilClass.createPatientEntity())
                        .doctor(AccountUtilClass.createDoctorEntity())
                        .appointmentStatus(AppointmentStatus.CONFIRMED)
                        .timeSlot(TimeSlotEntity.builder()
                                .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                                .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                                .doctor(AccountUtilClass.createDoctorEntity())
                                .build())
                        .build();



        when(appointmentRepositoryMock.save(any(AppointmentEntity.class))).thenReturn(appointment);

        //Act
        CreateAppointmentResponse actualResult = createAppointmentUseCase.createAppointment(request);

        //Assert
        CreateAppointmentResponse expectedResult = CreateAppointmentResponse.builder().id(id).build();

        assertEquals(actualResult, expectedResult);
        verify(appointmentRepositoryMock).save(any(AppointmentEntity.class));
    }
}