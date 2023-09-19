package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.CreateAppointmentResponse;

public interface CreateAppointmentUseCase {
    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
}
