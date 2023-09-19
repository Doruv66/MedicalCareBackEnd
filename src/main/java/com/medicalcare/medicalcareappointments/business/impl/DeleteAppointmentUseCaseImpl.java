package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.DeleteAppointmentUseCase;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAppointmentUseCaseImpl implements DeleteAppointmentUseCase{
    private final AppointmentRepository appointmentRepository;
    @Override
    public void deleteAppointment(long id) {
        appointmentRepository.deleteById(id);
    }
}
