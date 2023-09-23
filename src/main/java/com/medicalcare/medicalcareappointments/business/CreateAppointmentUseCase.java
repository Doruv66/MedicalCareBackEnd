package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentResponse;

public interface CreateAppointmentUseCase {
    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
}
