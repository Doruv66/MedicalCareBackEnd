package com.medicalcare.medicalcareappointments.domain.account;

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
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDoctorRequest extends UpdateAccountRequest {
    @NotBlank
    private String specialization;
    private String photo;
    private String name;
    private String fname;
    private String description;
    private List<TimeSlot> availableTimeSlots;
}
