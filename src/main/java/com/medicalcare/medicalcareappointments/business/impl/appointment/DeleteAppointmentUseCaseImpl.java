package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.DeleteAppointmentUseCase;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
import com.medicalcare.medicalcareappointments.exception.NotFoundAppointmentException;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.TimeSlotRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteAppointmentUseCaseImpl implements DeleteAppointmentUseCase{
    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;
    @Transactional
    @Override
    public void deleteAppointment(long id) {
        Optional<AppointmentEntity> appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()) {
            throw new NotFoundAppointmentException("NOT_FOUND_APPOINTMENT");
        }
        appointment.get().getTimeSlot().setTimeSlotType(TimeSlotType.AVAILABLE);
        timeSlotRepository.save(appointment.get().getTimeSlot());
        appointmentRepository.deleteById(id);
    }
}
