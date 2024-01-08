package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, Long> {
    @Query("SELECT t FROM TimeSlotEntity t " +
            "WHERE DATE(t.startTime) = DATE(:targetDate) " +
            "AND t.doctor.accountId = :doctorId " +
            "AND t.timeSlotType = 'AVAILABLE'")
    List<TimeSlotEntity> findTimeSlotsByDateAndDoctorId(
            @Param("targetDate") Timestamp targetDate,
            @Param("doctorId") Long doctorId
    );
}
