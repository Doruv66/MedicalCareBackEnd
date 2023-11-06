package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;

public class ReverseTimeSlotConverter {
    private ReverseTimeSlotConverter(){}
    public static TimeSlotEntity convert(TimeSlot timeSlot){
        return TimeSlotEntity.builder()
                .timeSlotId(timeSlot.getTimeSlotId())
                .doctor(DoctorEntity.builder().accountId(timeSlot.getDoctor().getAccountId()).build())
//                        .description(timeSlot.getDoctor().getDescription())
//                        .name(timeSlot.getDoctor().getName())
//                        .fname(timeSlot.getDoctor().getFname())
//                        .photo(timeSlot.getDoctor().getPhoto())
//                        .specialization(timeSlot.getDoctor().getSpecialization())
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .build();
    }
}
