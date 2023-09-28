package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentConverterTest {
    @Test
    void convertAppointment_shouldConvertAppointmentEntity() {
        //Arrange
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.Confirmed)
                .dateTime(new Date(2011, 11, 11))
                .doctorId(2L)
                .userId(3L)
                .build();

        //Act
        Appointment appointment = AppointmentConverter.convert(appointmentEntity);

        //Assert
        assertEquals(appointmentEntity.getAppointmentId(), appointment.getAppointmentId());
        assertEquals(appointmentEntity.getAppointmentStatus(), appointment.getAppointmentStatus());
        assertEquals(appointmentEntity.getDateTime(), appointment.getDateTime());
        assertEquals(appointmentEntity.getUserId(), appointment.getUserId());
        assertEquals(appointmentEntity.getDoctorId(), appointment.getDoctorId());
    }
}