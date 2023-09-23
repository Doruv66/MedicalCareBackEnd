package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.UpdateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.UpdateAppointmentResponse;

public interface UpdateAppointmentUseCase {
    UpdateAppointmentResponse updateAppointment(UpdateAppointmentRequest request, long id);
}
