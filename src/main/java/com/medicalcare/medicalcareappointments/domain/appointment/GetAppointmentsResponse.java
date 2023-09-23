package com.medicalcare.medicalcareappointments.domain.appointment;


import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAppointmentsResponse {
    private List<Appointment> appointments;
}
