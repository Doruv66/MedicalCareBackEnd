package com.medicalcare.medicalcareappointments.domain.review;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.Patient;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {

    @NotNull
    private int rating;

    @NotBlank
    private String comment;

    @NotNull
    private Patient patient;

    @NotNull
    private Timestamp date;

    @NotNull
    private Doctor doctor;

    @NotNull
    private Appointment appointment;
}
