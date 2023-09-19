package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository {
    List<ReviewEntity> findAll();

    ReviewEntity save(ReviewEntity review);

    void deleteById(long reviewId);

    Optional<ReviewEntity> findById(long reviewId);
}
