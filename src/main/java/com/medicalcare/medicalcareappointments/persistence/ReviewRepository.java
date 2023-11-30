package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT AVG(r.rating) FROM ReviewEntity r WHERE r.doctor.accountId = :doctorAccountId")
    Double getAverageRatingForDoctor(@Param("doctorAccountId") Long doctorAccountId);

    List<ReviewEntity> findByDoctor_AccountId(Long doctorAccountId);
}
