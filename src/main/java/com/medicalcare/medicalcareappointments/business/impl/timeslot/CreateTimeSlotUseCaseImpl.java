package com.medicalcare.medicalcareappointments.business.impl.timeslot;

import com.medicalcare.medicalcareappointments.business.CreateTimeSlotUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotRequest;
import com.medicalcare.medicalcareappointments.domain.timeslot.CreateTimeSlotResponse;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateTimeSlotUseCaseImpl implements CreateTimeSlotUseCase {

    private final TimeSlotRepository timeSlotRepository;

    @Transactional
    @Override
    public CreateTimeSlotResponse createTimeSlot(CreateTimeSlotRequest request) {
        // Check if time slots already exist for the requested date and doctor
        List<TimeSlotEntity> existingTimeSlots = timeSlotRepository
                .findTimeSlotsByDateAndDoctorId(request.getDate(), request.getDoctor().getAccountId());

        if (!existingTimeSlots.isEmpty()) {
            throw new IllegalArgumentException("Time slots already exist for this date and doctor.");
        }

        // Generate time slots for the requested date
        generateTimeSlotsForDate(request.getDate(), (DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()));

        return CreateTimeSlotResponse.builder().build();
    }

    private void generateTimeSlotsForDate(Timestamp date, DoctorEntity doctor) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        for (int hour = 9; hour <= 17; hour++) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date start = calendar.getTime();

            calendar.add(Calendar.HOUR, 1);
            Date end = calendar.getTime();

            Timestamp startTime = new Timestamp(start.getTime());
            Timestamp endTime = new Timestamp(end.getTime());

            TimeSlotEntity timeSlot = TimeSlotEntity.builder()
                    .startTime(startTime)
                    .endTime(endTime)
                    .doctor(doctor)
                    .timeSlotType(TimeSlotType.AVAILABLE)
                    .build();
            timeSlotRepository.save(timeSlot);
        }
    }
}
