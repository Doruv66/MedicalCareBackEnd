package com.medicalcare.medicalcareappointments.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAppointmentCountResponse {
    private int totalCountOfAppointments;
    private int bookedAppointments;
}
