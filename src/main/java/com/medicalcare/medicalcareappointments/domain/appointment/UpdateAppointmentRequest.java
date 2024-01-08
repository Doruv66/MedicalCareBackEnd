package com.medicalcare.medicalcareappointments.domain.appointment;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.Patient;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAppointmentRequest {
    @NotNull
    private TimeSlot timeSlot;
    @NotNull
    private Patient patient;
    @NotNull
    private Doctor doctor;
    @NotNull
    private AppointmentStatus appointmentStatus;
}
