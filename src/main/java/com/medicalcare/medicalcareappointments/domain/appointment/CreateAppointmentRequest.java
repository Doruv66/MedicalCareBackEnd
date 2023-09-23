package com.medicalcare.medicalcareappointments.domain.appointment;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateAppointmentRequest {
    @NotNull
    private Date dateTime;
    @NotNull
    private Long userId;
    @NotNull
    private Long doctorId;
    @NotNull
    private AppointmentStatus appointmentStatus;
}
