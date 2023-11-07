package com.medicalcare.medicalcareappointments.domain.timeslot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimeSlotResponse {
    private long id;
}
