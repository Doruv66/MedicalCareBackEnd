package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.TimeSlotEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppointmentRepositoryTest {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void save_shouldPersistAppointmentEntity() {
        DoctorEntity doctor = DoctorEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("john@example.com")
                .password("password")
                .accountType(AccountType.DOCTOR)
                .specialization("Cardiology")
                .photo("photo.jpg")
                .description("Description")
                .build();

        PatientEntity patient = PatientEntity.builder()
                .firstName("Alice")
                .lastName("Smith")
                .username("alice")
                .email("alice@example.com")
                .password("password")
                .accountType(AccountType.PATIENT)
                .dateOfBirth(Timestamp.valueOf("1990-01-01 00:00:00"))
                .build();

        DoctorEntity savedDoctor = entityManager.merge(doctor);
        PatientEntity savedPatient = entityManager.merge(patient);

        TimeSlotEntity timeSlot = TimeSlotEntity.builder()
                .startTime(Timestamp.valueOf("2023-12-01 09:00:00"))
                .endTime(Timestamp.valueOf("2023-12-01 10:00:00"))
                .doctor(savedDoctor)
                .timeSlotType(TimeSlotType.AVAILABLE)
                .build();

        TimeSlotEntity savedTimeSlot = entityManager.merge(timeSlot);

        AppointmentEntity appointment = AppointmentEntity.builder()
                .timeSlot(savedTimeSlot)
                .patient(savedPatient)
                .doctor(savedDoctor)
                .appointmentStatus(AppointmentStatus.COMPLETED)
                .build();

        appointmentRepository.save(appointment);

        Optional<AppointmentEntity> savedAppointment = appointmentRepository.findById(appointment.getAppointmentId());

        assertTrue(savedAppointment.isPresent());
        assertEquals(AppointmentStatus.COMPLETED, savedAppointment.get().getAppointmentStatus());
    }

    @Test
    void findAll_shouldReturnAllAppointments() {
        DoctorEntity doctor = DoctorEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("john@example.com")
                .password("password")
                .accountType(AccountType.DOCTOR)
                .specialization("Cardiology")
                .photo("photo.jpg")
                .description("Description")
                .build();

        PatientEntity patient = PatientEntity.builder()
                .firstName("Alice")
                .lastName("Smith")
                .username("alice")
                .email("alice@example.com")
                .password("password")
                .accountType(AccountType.PATIENT)
                .dateOfBirth(Timestamp.valueOf("1990-01-01 00:00:00"))
                .build();

        DoctorEntity savedDoctor = entityManager.merge(doctor);
        PatientEntity savedPatient = entityManager.merge(patient);

        TimeSlotEntity timeSlot1 = TimeSlotEntity.builder()
                .startTime(Timestamp.valueOf("2023-12-01 09:00:00"))
                .endTime(Timestamp.valueOf("2023-12-01 10:00:00"))
                .doctor(savedDoctor)
                .timeSlotType(TimeSlotType.AVAILABLE)
                .build();

        TimeSlotEntity timeSlot2 = TimeSlotEntity.builder()
                .startTime(Timestamp.valueOf("2023-12-02 09:00:00"))
                .endTime(Timestamp.valueOf("2023-12-02 10:00:00"))
                .doctor(savedDoctor)
                .timeSlotType(TimeSlotType.AVAILABLE)
                .build();

        TimeSlotEntity savedTimeSlot1 = entityManager.merge(timeSlot1);
        TimeSlotEntity savedTimeSlot2 = entityManager.merge(timeSlot2);

        AppointmentEntity appointment1 = AppointmentEntity.builder()
                .timeSlot(savedTimeSlot1)
                .patient(savedPatient)
                .doctor(savedDoctor)
                .appointmentStatus(AppointmentStatus.COMPLETED)
                .build();

        AppointmentEntity appointment2 = AppointmentEntity.builder()
                .timeSlot(savedTimeSlot2)
                .patient(savedPatient)
                .doctor(savedDoctor)
                .appointmentStatus(AppointmentStatus.COMPLETED)
                .build();

        appointmentRepository.saveAll(List.of(appointment1, appointment2));

        List<AppointmentEntity> allAppointments = appointmentRepository.findAll();

        assertEquals(2, allAppointments.size());
    }
}