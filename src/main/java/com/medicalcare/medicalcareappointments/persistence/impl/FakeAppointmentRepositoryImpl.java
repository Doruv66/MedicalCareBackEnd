package com.medicalcare.medicalcareappointments.persistence.impl;

import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeAppointmentRepositoryImpl implements AppointmentRepository {

    private static long NEXT_ID = 1;

    private final List<AppointmentEntity> savedAppointments;

    public FakeAppointmentRepositoryImpl(){this.savedAppointments = new ArrayList<>();}

    @Override
    public List<AppointmentEntity> findAll() {
        return Collections.unmodifiableList(this.savedAppointments);
    }

    @Override
    public AppointmentEntity save(AppointmentEntity appointment) {
        if(appointment.getAppointmentId() == null){
            appointment.setAppointmentId(NEXT_ID);
            NEXT_ID++;
            this.savedAppointments.add(appointment);
        }
        return appointment;
    }

    @Override
    public void deleteById(long appointmentId) {
        this.savedAppointments.removeIf(appointment -> appointment.getAppointmentId().equals(appointmentId));
    }

    @Override
    public Optional<AppointmentEntity> findById(long appointmentId) {
        return this.savedAppointments.stream()
                .filter(appointment -> appointment.getAppointmentId().equals(appointmentId))
                .findFirst();
    }
}
