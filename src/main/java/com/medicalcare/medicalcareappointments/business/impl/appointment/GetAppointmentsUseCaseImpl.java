package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.GetAppointmentsUseCase;
import com.medicalcare.medicalcareappointments.business.impl.appointment.AppointmentConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentsResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAppointmentsUseCaseImpl implements GetAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;

    @Override
    public GetAppointmentsResponse getAppointments() {
        GetAppointmentsResponse response = new GetAppointmentsResponse();
        List<Appointment> appointments = appointmentRepository.findAll()
                .stream()
                .map(AppointmentConverter::convert)
                .toList();
        response.setAppointments(appointments);
        return response;
    }
}
