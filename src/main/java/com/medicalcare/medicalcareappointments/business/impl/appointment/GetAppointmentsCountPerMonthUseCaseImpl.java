package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.GetAppointmentsCountPerMonthUseCase;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.GetCountPerMonthResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class GetAppointmentsCountPerMonthUseCaseImpl implements GetAppointmentsCountPerMonthUseCase {
    private final AppointmentRepository appointmentRepository;
    @Transactional
    @Override
    public GetCountPerMonthResponse getCountPerMonth() {
        List<Appointment> appointments = appointmentRepository.findAll().stream()
                .map(AppointmentConverter::convert)
                .toList();

        Map<String, Integer> countPerMonth = new LinkedHashMap<>();

        for (Appointment appointment : appointments) {
            String month = getMonthFromTimestamp(appointment.getTimeSlot().getStartTime());
            countPerMonth.put(month, countPerMonth.getOrDefault(month, 0) + 1);
        }

        return GetCountPerMonthResponse.builder().countMonth(countPerMonth).build();
    }

    private String getMonthFromTimestamp(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.getMonth().toString();
    }
}
