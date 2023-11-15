package com.medicalcare.medicalcareappointments.domain.timeslot;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class TimeSlot {
    private Long timeSlotId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Doctor doctor;
    private TimeSlotType timeSlotType;
}
