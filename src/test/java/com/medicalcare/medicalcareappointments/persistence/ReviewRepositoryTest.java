package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
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
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void getAverageRatingForDoctor_shouldReturnCorrectAverageRating() {
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

        ReviewEntity review1 = ReviewEntity.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .rating(4)
                .comment("Good doctor")
                .doctor(savedDoctor)
                .patient(savedPatient)
                .build();

        ReviewEntity review2 = ReviewEntity.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .rating(5)
                .comment("Excellent service")
                .doctor(savedDoctor)
                .patient(savedPatient)
                .build();

        ReviewEntity savedReview1 = entityManager.merge(review1);
        ReviewEntity savedReview2 = entityManager.merge(review2);

        Double averageRating = reviewRepository.getAverageRatingForDoctor(savedDoctor.getAccountId());
        assertEquals(4.5, averageRating, 0.01);
    }

    @Test
    void findByDoctor_AccountId_shouldReturnReviewsForDoctor() {
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

        ReviewEntity review1 = ReviewEntity.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .rating(4)
                .comment("Good doctor")
                .doctor(savedDoctor)
                .patient(savedPatient)
                .build();

        ReviewEntity review2 = ReviewEntity.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .rating(5)
                .comment("Excellent service")
                .doctor(savedDoctor)
                .patient(savedPatient)
                .build();

        ReviewEntity savedReview1 = entityManager.merge(review1);
        ReviewEntity savedReview2 = entityManager.merge(review2);

        List<ReviewEntity> reviews = reviewRepository.findByDoctor_AccountId(savedDoctor.getAccountId());

        assertEquals(2, reviews.size());
    }

    @Test
    void save_shouldPersistReviewEntity() {
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

        ReviewEntity review = ReviewEntity.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .rating(4)
                .comment("Good doctor")
                .doctor(savedDoctor)
                .patient(savedPatient)
                .build();

        ReviewEntity savedReview = reviewRepository.save(review);

        Optional<ReviewEntity> retrievedReview = reviewRepository.findById(savedReview.getReviewId());

        assertTrue(retrievedReview.isPresent());
        assertEquals("Good doctor", retrievedReview.get().getComment());
    }
}