package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentResponse;


public interface GetAppointmentUseCase {
    GetAppointmentResponse getAppointment(long id);
}
