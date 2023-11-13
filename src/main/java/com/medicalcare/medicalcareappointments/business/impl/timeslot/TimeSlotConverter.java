package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;

import java.util.ArrayList;

public class TimeSlotConverter {

    private TimeSlotConverter(){}

    public static TimeSlot convert(TimeSlotEntity timeSlot){
        return TimeSlot.builder()
                .timeSlotId(timeSlot.getTimeSlotId())
                .doctor(Doctor.builder()
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
