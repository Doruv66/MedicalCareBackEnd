package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
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
class TimeSlotRepositoryTest {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void save_shouldPersistTimeSlotEntity() {
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

        DoctorEntity savedDoctor = entityManager.merge(doctor);

        TimeSlotEntity timeSlot = TimeSlotEntity.builder()
                .startTime(new Timestamp(System.currentTimeMillis()))
                .endTime(new Timestamp(System.currentTimeMillis() + 3600000)) // One hour duration
                .doctor(savedDoctor)
                .timeSlotType(TimeSlotType.AVAILABLE)
                .build();

        TimeSlotEntity savedTimeSlot = timeSlotRepository.save(timeSlot);

        Optional<TimeSlotEntity> retrievedTimeSlot = timeSlotRepository.findById(savedTimeSlot.getTimeSlotId());

        assertTrue(retrievedTimeSlot.isPresent());
        assertEquals(TimeSlotType.AVAILABLE, retrievedTimeSlot.get().getTimeSlotType());
    }

    @Test
    void findTimeSlotsByDateAndDoctorId_shouldReturnMatchingTimeSlots() {
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

        DoctorEntity savedDoctor = entityManager.merge(doctor);

        Timestamp targetDate = Timestamp.valueOf("2023-12-31 00:00:00");

        TimeSlotEntity timeSlot1 = TimeSlotEntity.builder()
                .startTime(targetDate)
                .endTime(new Timestamp(targetDate.getTime() + 3600000))
                .doctor(savedDoctor)
                .timeSlotType(TimeSlotType.AVAILABLE)
                .build();

        TimeSlotEntity timeSlot2 = TimeSlotEntity.builder()
                .startTime(new Timestamp(targetDate.getTime() + 7200000))
                .endTime(new Timestamp(targetDate.getTime() + 10800000))
                .doctor(savedDoctor)
                .timeSlotType(TimeSlotType.AVAILABLE)
                .build();

        entityManager.persist(timeSlot1);
        entityManager.persist(timeSlot2);

        List<TimeSlotEntity> foundTimeSlots = timeSlotRepository.findTimeSlotsByDateAndDoctorId(targetDate, savedDoctor.getAccountId());

        assertEquals(2, foundTimeSlots.size());
    }
}