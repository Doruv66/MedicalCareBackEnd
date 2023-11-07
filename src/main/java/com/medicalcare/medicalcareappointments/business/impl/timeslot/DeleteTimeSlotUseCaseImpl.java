package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.DeleteTimeSlotUseCase;
import com.medicalcare.medicalcareappointments.exception.NotFoundTimeSlotException;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTimeSlotUseCaseImpl implements DeleteTimeSlotUseCase {

    private final TimeSlotRepository timeSlotRepository;

    @Override
    public void deleteTimeSlot(long id) {
        if(timeSlotRepository.findById(id).isEmpty()) {
            throw new NotFoundTimeSlotException("NOT_FOUND_TIMESLOT");
        }
        timeSlotRepository.deleteById(id);
    }
}
