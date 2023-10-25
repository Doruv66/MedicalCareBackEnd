package com.medicalcare.medicalcareappointments.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class DoctorEntity extends AccountEntity{
    private String specialization;
    private String photo;
    private String name;
    private String fname;
    private String description;
    private List<TimeSlotEntity> availableTimeSlots;
}
