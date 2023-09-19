package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    List<AppointmentEntity> findAll();

    AppointmentEntity save(AppointmentEntity appointment);

    void deleteById(long appointmentId);

    Optional<AppointmentEntity> findById(long appointmentId);
}
