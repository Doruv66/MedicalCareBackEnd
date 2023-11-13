package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.GetAppointmentUseCase;
import com.medicalcare.medicalcareappointments.domain.appointment.Appointment;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentResponse;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAppointmentUseCaseImpl implements GetAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    @Override
    public GetAppointmentResponse getAppointment(long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id).map(AppointmentConverter::convert);
        if(appointment.isEmpty()) {
            throw new NotFoundAppointmentException("NOT_FOUND");
        }
        return GetAppointmentResponse.builder().appointment(appointment.get()).build();
    }
}
