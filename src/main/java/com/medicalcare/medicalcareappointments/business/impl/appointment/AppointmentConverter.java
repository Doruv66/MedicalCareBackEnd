package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.Patient;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;

final class AppointmentConverter {
    private AppointmentConverter(){}

    public static Appointment convert(AppointmentEntity appointment){
        return Appointment.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentStatus(appointment.getAppointmentStatus())
                .patient((Patient) AccountConverter.convert(appointment.getPatient()))
                .doctor((Doctor) AccountConverter.convert(appointment.getDoctor()))
                .dateTime(appointment.getDateTime())
                .build();
    }
}
