package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.timeslot.GetTimeSlotsResponse;

import java.sql.Timestamp;

public interface GetTimeSlotsByDateUseCase {
    GetTimeSlotsResponse getTimeSlotsByDate(Timestamp timestamp, long doctorId);
}
