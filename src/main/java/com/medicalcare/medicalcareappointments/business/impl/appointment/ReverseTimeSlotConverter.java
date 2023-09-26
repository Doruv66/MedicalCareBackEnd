package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.domain.appointment.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;

public class ReverseTimeSlotConverter {

    private ReverseTimeSlotConverter(){}

    public static TimeSlotEntity convert(TimeSlot timeSlot){
        return TimeSlotEntity.builder()
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .build();
    }
}
