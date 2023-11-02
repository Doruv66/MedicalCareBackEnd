package com.medicalcare.medicalcareappointments.persistence.entity;

import com.medicalcare.medicalcareappointments.domain.account.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TimeSlotEntity {
    private Timestamp startTime;
    private Timestamp endTime;
}
