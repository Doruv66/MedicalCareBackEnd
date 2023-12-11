package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.GetUpcomingAppointmentsUseCase;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentsResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class GetUpcomingAppointmentsUseCaseImpl implements GetUpcomingAppointmentsUseCase {

    private final AppointmentRepository appointmentRepository;

    @Transactional
    @Override
    public GetAppointmentsResponse getUpcomingAppointments(long accId) {
        List<Appointment> upcomingAppointments = appointmentRepository
                .findUpcomingAppointmentsForPatient(accId, Timestamp.valueOf(LocalDateTime.now())).stream()
                .map(AppointmentConverter::convert)
                .toList();

        return GetAppointmentsResponse.builder()
                .appointments(upcomingAppointments)
                .build();
    }
}
