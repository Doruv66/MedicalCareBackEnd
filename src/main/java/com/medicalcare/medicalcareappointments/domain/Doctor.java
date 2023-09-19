package com.medicalcare.medicalcareappointments.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Account{
    private String specialization;
    private List<TimeSlot> availableTimeSlots;
}

