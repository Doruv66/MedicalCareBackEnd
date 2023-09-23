package com.medicalcare.medicalcareappointments.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TimeSlot {
    private Date startTime;
    private Date endTime;
}
