package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.GetTimeSlotsByDateUseCase;
import com.medicalcare.medicalcareappointments.domain.timeslot.GetTimeSlotsResponse;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GetTimeSlotsByDateUseCaseImpl implements GetTimeSlotsByDateUseCase {
    private final TimeSlotRepository timeSlotRepository;
    @Override
    public GetTimeSlotsResponse getTimeSlotsByDate(Timestamp timestamp, long doctorId) {
        LocalDate targetDate = timestamp.toLocalDateTime().toLocalDate();
        List<TimeSlotEntity> timeSlots = timeSlotRepository.findTimeSlotsByDateAndDoctorId(Timestamp.valueOf(targetDate.atStartOfDay()), doctorId);
        return GetTimeSlotsResponse.builder().timeSlots(timeSlots.stream().map(TimeSlotConverter::convert).toList()).build();
    }
}
