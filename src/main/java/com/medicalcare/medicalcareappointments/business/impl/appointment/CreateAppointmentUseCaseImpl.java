package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.CreateAppointmentUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
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
                .doctor((DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()))
                .patient((PatientEntity) ReverseAccountConverter.convert(request.getPatient()))
                .build();
        return appointmentRepository.save(newAppointment);
    }
}
