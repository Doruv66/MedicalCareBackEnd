package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;

final class AppointmentConverter {
    private AppointmentConverter(){}

    public static Appointment convert(AppointmentEntity appointment){
        return Appointment.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentStatus(appointment.getAppointmentStatus())
                .user((User) AccountConverter.convert(appointment.getUser()))
                .doctor((Doctor) AccountConverter.convert(appointment.getDoctor()))
                .dateTime(appointment.getDateTime())
                .build();
    }
}
