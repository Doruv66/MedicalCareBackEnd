package com.medicalcare.medicalcareappointments.domain.timeslot;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import jakarta.persistence.*;
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
public class CreateTimeSlotRequest {

    @NotNull
    private Long timeSlotId;

    @NotNull
    private Timestamp startTime;

    @NotNull
    private Timestamp endTime;

    @NotBlank
    private Doctor doctor;
}
