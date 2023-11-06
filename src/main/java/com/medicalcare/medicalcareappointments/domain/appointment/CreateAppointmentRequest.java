package com.medicalcare.medicalcareappointments.domain.appointment;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
public class CreateAppointmentRequest {
    @NotNull
    private Timestamp dateTime;
    @NotNull
    private User user;
    @NotNull
    private Doctor doctor;
    @NotNull
    private AppointmentStatus appointmentStatus;
}
