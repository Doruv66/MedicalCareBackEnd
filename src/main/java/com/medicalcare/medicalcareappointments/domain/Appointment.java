package com.medicalcare.medicalcareappointments.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Long appointmentId;
    private Date dateTime;
    private Long userId;
    private Long doctorId;
    private AppointmentStatus appointmentStatus;
}
