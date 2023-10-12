package com.medicalcare.medicalcareappointments.domain.account;

import com.medicalcare.medicalcareappointments.domain.appointment.TimeSlot;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Account {
    private String specialization;
    private String photo;
    private String name;
    private String fname;
    private List<TimeSlot> availableTimeSlots;
}

