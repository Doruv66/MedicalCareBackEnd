package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
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
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findByAccountType_shouldReturnCorrectAccountEntities() {
        DoctorEntity doctorAccount = DoctorEntity.builder()
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

        PatientEntity patientAccount = PatientEntity.builder()
                .firstName("Alice")
                .lastName("Smith")
                .username("alice")
                .email("alice@example.com")
                .password("password")
                .accountType(AccountType.PATIENT)
                .dateOfBirth(Timestamp.valueOf("1990-01-01 00:00:00"))
                .build();

        AdminEntity adminAccount = AdminEntity.builder()
                .firstName("Bob")
                .lastName("Johnson")
                .username("bob")
                .email("bob@example.com")
                .password("password")
                .accountType(AccountType.ADMIN)
                .position("Manager")
                .build();

        accountRepository.saveAll(List.of(doctorAccount, patientAccount, adminAccount));

        List<AccountEntity> foundDoctors = accountRepository.findByAccountType(AccountType.DOCTOR);
        List<AccountEntity> foundPatients = accountRepository.findByAccountType(AccountType.PATIENT);
        List<AccountEntity> foundAdmins = accountRepository.findByAccountType(AccountType.ADMIN);

        assertEquals(1, foundDoctors.size());
        assertEquals(1, foundPatients.size());
        assertEquals(1, foundAdmins.size());

        assertEquals(AccountType.DOCTOR, foundDoctors.get(0).getAccountType());
        assertEquals(AccountType.PATIENT, foundPatients.get(0).getAccountType());
        assertEquals(AccountType.ADMIN, foundAdmins.get(0).getAccountType());
    }

    @Test
    void findByEmail_shouldReturnCorrectAccountEntity() {
        DoctorEntity doctor = DoctorEntity.builder()
                .firstName("Michael")
                .lastName("Jordan")
                .username("mjordan")
                .email("mjordan@example.com")
                .password("password")
                .accountType(AccountType.DOCTOR)
                .specialization("Orthopedics")
                .photo("photo.jpg")
                .description("Description")
                .build();

        accountRepository.save(doctor);

        Optional<AccountEntity> foundDoctor = accountRepository.findByEmail("mjordan@example.com");

        assertTrue(foundDoctor.isPresent());
        assertEquals("mjordan@example.com", foundDoctor.get().getEmail());
    }

    @Test
    void findByUsername_shouldReturnCorrectAccountEntity() {
        PatientEntity patient = PatientEntity.builder()
                .firstName("Emma")
                .lastName("Thompson")
                .username("emma")
                .email("emma@example.com")
                .password("password")
                .accountType(AccountType.PATIENT)
                .dateOfBirth(Timestamp.valueOf("1985-05-15 00:00:00"))
                .build();

        accountRepository.save(patient);

        Optional<AccountEntity> foundPatient = accountRepository.findByUsername("emma");

        assertTrue(foundPatient.isPresent());
        assertEquals("emma", foundPatient.get().getUsername());
    }

    @Test
    void findByKeyword_shouldReturnMatchingDoctorEntities() {
        DoctorEntity doctor = DoctorEntity.builder()
                .firstName("David")
                .lastName("Beckham")
                .username("david")
                .email("david@example.com")
                .password("password")
                .accountType(AccountType.DOCTOR)
                .specialization("Dermatology")
                .photo("photo.jpg")
                .description("Description")
                .build();

        accountRepository.save(doctor);

        List<AccountEntity> foundDoctors = accountRepository.findByKeyword("Dermatology");

        assertEquals(1, foundDoctors.size());
        assertEquals("Dermatology", ((DoctorEntity)foundDoctors.get(0)).getSpecialization());
    }

    @Test
    void save_shouldPersistDoctorEntity() {
        DoctorEntity doctor = DoctorEntity.builder()
                .firstName("Steven")
                .lastName("Gerrard")
                .username("steven")
                .email("steven@example.com")
                .password("password")
                .accountType(AccountType.DOCTOR)
                .specialization("Pediatrics")
                .photo("photo.jpg")
                .description("Description")
                .build();

        accountRepository.save(doctor);

        Optional<AccountEntity> savedDoctor = accountRepository.findById(doctor.getAccountId());

        assertTrue(savedDoctor.isPresent());
        assertEquals("Pediatrics", ((DoctorEntity)savedDoctor.get()).getSpecialization());
    }


    @Test
    void save_shouldUpdateAccountEntity() {
        PatientEntity patient = PatientEntity.builder()
                .firstName("Alice")
                .lastName("Smith")
                .username("alice")
                .email("alice@example.com")
                .password("password")
                .accountType(AccountType.PATIENT)
                .dateOfBirth(Timestamp.valueOf("1990-01-01 00:00:00"))
                .build();

        PatientEntity savedPatient = accountRepository.save(patient);

        savedPatient.setEmail("updatedalice@example.com");
        accountRepository.save(savedPatient);

        Optional<AccountEntity> updatedPatient = accountRepository.findById(savedPatient.getAccountId());

        assertTrue(updatedPatient.isPresent());
        assertEquals("updatedalice@example.com", updatedPatient.get().getEmail());
    }

    @Test
    void save_shouldPersistAccountEntityUsingEntityManager() {
        AdminEntity admin = AdminEntity.builder()
                .firstName("Bob")
                .lastName("Johnson")
                .username("bob")
                .email("bob@example.com")
                .password("password")
                .accountType(AccountType.ADMIN)
                .position("Manager")
                .build();

        accountRepository.save(admin);

        Optional<AccountEntity> savedAdmin = accountRepository.findById(admin.getAccountId());

        assertTrue(savedAdmin.isPresent());
        assertEquals("bob@example.com", savedAdmin.get().getEmail());
    }

    @Test
    void save_shouldUpdateAccountEntityUsingEntityManager() {
        DoctorEntity doctor = DoctorEntity.builder()
                .firstName("Michael")
                .lastName("Jordan")
                .username("mjordan")
                .email("mjordan@example.com")
                .password("password")
                .accountType(AccountType.DOCTOR)
                .specialization("Orthopedics")
                .photo("photo.jpg")
                .description("Description")
                .build();

        DoctorEntity savedDoctor = accountRepository.save(doctor);

        savedDoctor.setSpecialization("Pediatrics");
        accountRepository.save(savedDoctor);

        Optional<AccountEntity> updatedDoctor = accountRepository.findById(savedDoctor.getAccountId());

        assertTrue(updatedDoctor.isPresent());
        assertEquals("Pediatrics", ((DoctorEntity)updatedDoctor.get()).getSpecialization());
    }
}
