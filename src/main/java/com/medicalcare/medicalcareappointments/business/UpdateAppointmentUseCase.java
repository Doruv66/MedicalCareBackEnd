package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentResponse;

public interface UpdateAppointmentUseCase {
    UpdateAppointmentResponse updateAppointment(UpdateAppointmentRequest request, long id);
}
