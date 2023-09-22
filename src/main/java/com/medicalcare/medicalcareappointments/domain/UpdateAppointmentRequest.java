package com.medicalcare.medicalcareappointments.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UpdateAppointmentRequest {
    @NotNull
    private Date dateTime;
    @NotNull
    private Long userId;
    @NotNull
    private Long doctorId;
    @NotNull
    private AppointmentStatus appointmentStatus;
}
