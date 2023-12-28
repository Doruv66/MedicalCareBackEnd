package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> getAppointmentEntitiesByDoctorAccountId(long doctorId);

    @Query("SELECT a FROM AppointmentEntity a " +
            "JOIN FETCH a.timeSlot ts " +
            "WHERE a.patient.accountId = :patientId " +
            "AND ts.startTime > :today " +
            "ORDER BY ts.startTime ASC")
    List<AppointmentEntity> findUpcomingAppointmentsForPatient(@Param("patientId") Long patientId, @Param("today") Timestamp today);

    @Query("SELECT a FROM AppointmentEntity a " +
            "JOIN FETCH a.timeSlot ts " +
            "WHERE a.patient.accountId = :patientId " +
            "AND ts.startTime < :today " +
            "ORDER BY ts.startTime DESC")
    List<AppointmentEntity> findPreviousAppointmentsForPatient(@Param("patientId") Long patientId, @Param("today") Timestamp today);

    @Query("SELECT COUNT(a) FROM AppointmentEntity a " +
            "JOIN a.timeSlot ts " +
            "WHERE DATE(ts.startTime) = DATE(:day)")
    Optional<Integer> getCountOfAppointmentsByDay(
            @Param("day") Timestamp day
    );
}
