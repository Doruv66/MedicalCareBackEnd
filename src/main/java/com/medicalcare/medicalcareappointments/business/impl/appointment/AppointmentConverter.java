package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;

final class AppointmentConverter {
    private AppointmentConverter(){}

    public static Appointment convert(AppointmentEntity appointment){
        return Appointment.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentStatus(appointment.getAppointmentStatus())
                .userId(appointment.getUserId())
                .doctorId(appointment.getDoctorId())
                .dateTime(appointment.getDateTime())
                .build();
    }
}
