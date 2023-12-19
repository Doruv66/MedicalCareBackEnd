package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.GetDoctorAppointmentsUseCase;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentsResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class GetDoctorAppointmentsUseCaseImpl implements GetDoctorAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;

    @Transactional
    @Override
    public GetAppointmentsResponse getDoctorAppointments(long doctorId) {
        List<Appointment> appointments = appointmentRepository.getAppointmentEntitiesByDoctorAccountId(doctorId)
                .stream()
                .map(AppointmentConverter::convert)
                .toList();

        return GetAppointmentsResponse.builder()
                .appointments(appointments)
                .build();
     }
}
