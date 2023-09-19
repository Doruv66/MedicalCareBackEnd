package com.medicalcare.medicalcareappointments.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoctorRequest extends CreateAccountRequest {

    @NotBlank
    private String specialization;

    private List<TimeSlot> availableTimeSlots;
}
