package com.medicalcare.medicalcareappointments.domain.timeslot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTimeSlotsResponse {
    private List<TimeSlot> timeSlots;
}
