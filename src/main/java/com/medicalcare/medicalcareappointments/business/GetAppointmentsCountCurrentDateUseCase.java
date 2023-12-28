package com.medicalcare.medicalcareappointments.business;


import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentCountResponse;

public interface GetAppointmentsCountCurrentDateUseCase {
    GetAppointmentCountResponse getAppointmentCount();
}
