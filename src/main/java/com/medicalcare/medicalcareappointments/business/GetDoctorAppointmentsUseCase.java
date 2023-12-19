package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentsResponse;

public interface GetDoctorAppointmentsUseCase {
    GetAppointmentsResponse getDoctorAppointments(long doctorId);
}
