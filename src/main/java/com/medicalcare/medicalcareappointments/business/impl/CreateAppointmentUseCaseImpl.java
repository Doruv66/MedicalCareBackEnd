package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.CreateAppointmentUseCase;
import com.medicalcare.medicalcareappointments.domain.Appointment;
import com.medicalcare.medicalcareappointments.domain.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.CreateAppointmentResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;

    @Override
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        AppointmentEntity savedAppointment = saveNewAppointment(request);

        return CreateAppointmentResponse.builder()
                .id(savedAppointment.getAppointmentId())
                .build();
    }

    private AppointmentEntity saveNewAppointment(CreateAppointmentRequest request){
        AppointmentEntity newAppointment = AppointmentEntity.builder()
                .appointmentStatus(request.getAppointmentStatus())
                .dateTime(request.getDateTime())
                .doctorId(request.getDoctorId())
                .userId(request.getUserId())
                .build();
        return appointmentRepository.save(newAppointment);
    }
}
