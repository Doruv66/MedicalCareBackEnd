package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.CreateTimeSlotUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotRequest;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotResponse;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTimeSlotUseCaseImpl implements CreateTimeSlotUseCase {

    private final TimeSlotRepository timeSlotRepository;

    @Override
    public CreateTimeSlotResponse createTimeSlot(CreateTimeSlotRequest request) {
        TimeSlotEntity savedTimeSlot = saveNewTimeSlot(request);

        return CreateTimeSlotResponse.builder()
                .id(savedTimeSlot.getTimeSlotId())
                .build();
    }

    private TimeSlotEntity saveNewTimeSlot(CreateTimeSlotRequest request) {
        TimeSlotEntity newTimeSlot = TimeSlotEntity.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .doctor((DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()))
                .build();
        return timeSlotRepository.save(newTimeSlot);
    }
}
