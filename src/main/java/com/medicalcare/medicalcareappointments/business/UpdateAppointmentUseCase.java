package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.UpdateAppointmentRequest;

public interface UpdateAppointmentUseCase {
    void updateAppointment(UpdateAppointmentRequest request, long id);
}
