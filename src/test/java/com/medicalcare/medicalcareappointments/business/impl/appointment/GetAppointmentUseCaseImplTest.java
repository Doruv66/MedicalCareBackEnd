package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
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
        //Arrange
        long id = 1L;
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentId(id)
                .appointmentStatus(AppointmentStatus.CONFIRMED)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        when(appointmentRepositoryMock.findById(id))
                .thenReturn(Optional.ofNullable(appointmentEntity));


        //Act
        GetAppointmentResponse actualResult = getAppointmentUseCase.getAppointment(id);


        //Assert
        GetAppointmentResponse expectedResult = GetAppointmentResponse.builder().appointment(Appointment.builder()
                .appointmentId(id)
                .appointmentStatus(AppointmentStatus.CONFIRMED)
                .patient(AccountUtilClass.createPatient())
                .doctor(AccountUtilClass.createDoctor())
                .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build()).build();

        assertEquals(actualResult, expectedResult);
    }

}