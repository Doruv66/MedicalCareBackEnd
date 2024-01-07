package com.medicalcare.medicalcareappointments.domain.timeslot;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
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
    private Timestamp date;

    @NotNull
    private Doctor doctor;
}
