package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;

import javax.print.Doc;

public class TimeSlotConverter {

    private TimeSlotConverter(){}

    public static TimeSlot convert(TimeSlotEntity timeSlot){
        return TimeSlot.builder()
                .timeSlotId(timeSlot.getTimeSlotId())
                .doctor(Doctor.builder().accountId(timeSlot.getDoctor().getAccountId()).build())
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .build();
    }
}
