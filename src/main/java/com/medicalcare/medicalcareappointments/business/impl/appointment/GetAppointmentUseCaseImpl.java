package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.GetAppointmentUseCase;
import com.medicalcare.medicalcareappointments.business.impl.appointment.AppointmentConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAppointmentUseCaseImpl implements GetAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;
    @Override
    public Optional<Appointment> getAppointment(long id) {
        return appointmentRepository.findById(id).map(AppointmentConverter::convert);
    }
}
