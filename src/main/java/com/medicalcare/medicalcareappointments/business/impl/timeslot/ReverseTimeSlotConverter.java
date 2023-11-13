package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;

import java.util.ArrayList;

public class ReverseTimeSlotConverter {
    private ReverseTimeSlotConverter(){}
    public static TimeSlotEntity convert(TimeSlot timeSlot){
        return TimeSlotEntity.builder()
                .timeSlotId(timeSlot.getTimeSlotId())
                .doctor(DoctorEntity.builder()
                        .accountId(timeSlot.getDoctor().getAccountId())
                        .accountId(timeSlot.getDoctor().getAccountId())
                        .email(timeSlot.getDoctor().getEmail())
                        .lastName(timeSlot.getDoctor().getLastName())
                        .description(timeSlot.getDoctor().getDescription())
                        .specialization(timeSlot.getDoctor().getSpecialization())
                        .firstName(timeSlot.getDoctor().getFirstName())
                        .password(timeSlot.getDoctor().getPassword())
                        .photo(timeSlot.getDoctor().getPhoto())
                        .accountType(timeSlot.getDoctor().getAccountType())
                        .availableTimeSlots(new ArrayList<>())
                        .build())
                .startTime(timeSlot.getStartTime())
                .endTime(timeSlot.getEndTime())
                .build();
    }
}
