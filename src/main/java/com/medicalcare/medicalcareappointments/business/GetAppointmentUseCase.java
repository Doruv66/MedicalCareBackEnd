package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;

import java.util.Optional;

public interface GetAppointmentUseCase {
    Optional<Appointment> getAppointment(long id);
}
