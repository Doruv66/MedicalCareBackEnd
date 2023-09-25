package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.DeleteAppointmentUseCase;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAppointmentUseCaseImpl implements DeleteAppointmentUseCase{
    private final AppointmentRepository appointmentRepository;
    @Override
    public void deleteAppointment(long id) {
        if(appointmentRepository.findById(id).isEmpty()) {
            throw new NotFoundAppointmentException("NOT_FOUND_APPOINTMENT");
        }
        appointmentRepository.deleteById(id);
    }
}
