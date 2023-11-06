package com.medicalcare.medicalcareappointments.domain.account;

import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TimeSlot {
    private Long timeSlotId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Doctor doctor;
}
