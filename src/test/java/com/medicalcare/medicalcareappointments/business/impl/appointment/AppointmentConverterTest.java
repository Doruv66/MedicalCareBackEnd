package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.AccountUtilClass;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
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
                .appointmentStatus(AppointmentStatus.BOOKED)
                .patient(AccountUtilClass.createPatientEntity())
                .doctor(AccountUtilClass.createDoctorEntity())
                .timeSlot(TimeSlotEntity.builder()
                        .startTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .endTime(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                        .doctor(AccountUtilClass.createDoctorEntity())
                        .build())
                .build();

        //Act
        Appointment appointment = AppointmentConverter.convert(appointmentEntity);

        //Assert
        assertEquals(appointmentEntity.getAppointmentId(), appointment.getAppointmentId());
        assertEquals(appointmentEntity.getAppointmentStatus(), appointment.getAppointmentStatus());
        assertEquals(appointmentEntity.getTimeSlot(), ReverseTimeSlotConverter.convert(appointment.getTimeSlot()));
        assertEquals(AccountConverter.convert(appointmentEntity.getPatient()), appointment.getPatient());
        assertEquals(AccountConverter.convert(appointmentEntity.getDoctor()), appointment.getDoctor());
    }
}