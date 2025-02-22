package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.UpdateAppointmentUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentRequest;
import com.medicalcare.medicalcareappointments.domain.appointment.UpdateAppointmentResponse;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
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
            throw new NotFoundAppointmentException("NOT_FOUND_APPOINTMENT");
        }
        AppointmentEntity appointment = appointmentOptional.get();
        updateFields(request, appointment);

        return UpdateAppointmentResponse.builder()
                .id(appointment.getAppointmentId())
                .build();
    }

    private void updateFields(UpdateAppointmentRequest request, AppointmentEntity appointment){
        appointment.setAppointmentStatus(request.getAppointmentStatus());
        appointment.setTimeSlot(ReverseTimeSlotConverter.convert(request.getTimeSlot()));
        appointment.setPatient((PatientEntity) ReverseAccountConverter.convert(request.getPatient()));
        appointment.setDoctor((DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()));
        appointmentRepository.save(appointment);
    }
}
