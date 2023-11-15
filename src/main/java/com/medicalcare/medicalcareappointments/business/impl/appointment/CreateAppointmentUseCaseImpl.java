package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.CreateAppointmentUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.CreateAppointmentResponse;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Override
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        AppointmentEntity savedAppointment = saveNewAppointment(request);
        updateTimeSlot(savedAppointment);
        return CreateAppointmentResponse.builder()
                .id(savedAppointment.getAppointmentId())
                .build();
    }

    private AppointmentEntity saveNewAppointment(CreateAppointmentRequest request){
        AppointmentEntity newAppointment = AppointmentEntity.builder()
                .appointmentStatus(request.getAppointmentStatus())
                .timeSlot(ReverseTimeSlotConverter.convert(request.getTimeSlot()))
                .doctor((DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()))
                .patient((PatientEntity) ReverseAccountConverter.convert(request.getPatient()))
                .build();
        return appointmentRepository.save(newAppointment);
    }

    private void updateTimeSlot(AppointmentEntity appointment) {
        TimeSlotEntity timeSlot = TimeSlotEntity.builder()
                .timeSlotId(appointment.getTimeSlot().getTimeSlotId())
                .timeSlotType(TimeSlotType.UNAVAILABLE)
                .endTime(appointment.getTimeSlot().getEndTime())
                .startTime(appointment.getTimeSlot().getStartTime())
                .doctor(appointment.getTimeSlot().getDoctor())
                .build();
        timeSlotRepository.save(timeSlot);
    }
}
