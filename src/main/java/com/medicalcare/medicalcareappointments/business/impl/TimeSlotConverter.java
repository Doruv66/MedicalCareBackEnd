package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.domain.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;

public class TimeSlotConverter {

    private TimeSlotConverter(){}

    public static TimeSlot convert(TimeSlotEntity timeSlot){
        return TimeSlot.builder()
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .build();
    }
}
