package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentConverterTest {
    @Test
    void convertAppointment_shouldConvertAppointmentEntity() {
        //Arrange
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentId(1L)
                .appointmentStatus(AppointmentStatus.CONFIRMED)
                .user(AccountUtilClass.createUserEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .appointmentStatus(AppointmentStatus.CONFIRMED)
                .dateTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();

        //Act
        Appointment appointment = AppointmentConverter.convert(appointmentEntity);

        //Assert
        assertEquals(appointmentEntity.getAppointmentId(), appointment.getAppointmentId());
        assertEquals(appointmentEntity.getAppointmentStatus(), appointment.getAppointmentStatus());
        assertEquals(appointmentEntity.getDateTime(), appointment.getDateTime());
        assertEquals(AccountConverter.convert(appointmentEntity.getUser()), appointment.getUser());
        assertEquals(AccountConverter.convert(appointmentEntity.getDoctor()), appointment.getDoctor());
    }
}