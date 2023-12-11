package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentsResponse;

public interface GetPreviousAppointmentsUseCase {
    GetAppointmentsResponse getPreviousAppointments(long accId);
}
