package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotRequest;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotResponse;

public interface CreateTimeSlotUseCase {
    CreateTimeSlotResponse createTimeSlot(CreateTimeSlotRequest request);
}
