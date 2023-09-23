package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.UpdateAppointmentUseCase;
import com.medicalcare.medicalcareappointments.domain.UpdateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.UpdateAppointmentResponse;
import com.medicalcare.medicalcareappointments.exception.InvalidAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAppointmentUseCaseImpl implements UpdateAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    @Override
    public UpdateAppointmentResponse updateAppointment(UpdateAppointmentRequest request, long id) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findById(id);
        if(appointmentOptional.isEmpty()){
            throw new InvalidAppointmentException("APPOINTMENT_IS_INVALID");
        }
        AppointmentEntity appointment = appointmentOptional.get();
        updateFields(request, appointment);

        return UpdateAppointmentResponse.builder()
                .id(appointment.getAppointmentId())
                .build();
    }

    private void updateFields(UpdateAppointmentRequest request, AppointmentEntity appointment){
        appointment.setAppointmentStatus(request.getAppointmentStatus());
        appointment.setDateTime(request.getDateTime());
        appointment.setUserId(request.getUserId());
        appointment.setDoctorId(request.getDoctorId());
        appointmentRepository.save(appointment);
    }
}
