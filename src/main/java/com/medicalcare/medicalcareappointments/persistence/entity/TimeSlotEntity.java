package com.medicalcare.medicalcareappointments.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TimeSlotEntity {
    private Date startTime;
    private Date endTime;
}
