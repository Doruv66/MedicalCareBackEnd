package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.timeslot.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReverseAccountConverterTest {

    @Test
    void convertUser_shouldConvertUser() {

        //Arrange
        Patient patient = Patient.builder()
                .accountId(1L)
                .accountType(AccountType.PATIENT)
                .email("user@email.com")
                .username("username")
                .password("password")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .firstName("firstname")
                .lastName("lastname")
                .build();

        //Act
        AccountEntity patientEntity = ReverseAccountConverter.convert(patient);

        //Assert
        assertTrue(patientEntity instanceof PatientEntity);
        assertEquals(patient.getAccountId(), patientEntity.getAccountId());
        assertEquals(patient.getEmail(), patientEntity.getEmail());
        assertEquals(patient.getAccountType(), patientEntity.getAccountType());
        assertEquals(patient.getUsername(), patientEntity.getUsername());
        assertEquals(patient.getPassword(), patientEntity.getPassword());
        assertEquals(patient.getFirstName(), patientEntity.getFirstName());
        assertEquals(patient.getLastName(), patientEntity.getLastName());
        assertEquals(patient.getDateOfBirth(), ((PatientEntity) patientEntity).getDateOfBirth());
    }

    @Test
    void convertAdmin_shouldConvertAdmin() {
        //Arrange
        Admin admin = Admin.builder()
                .accountId(1L)
                .accountType(AccountType.ADMIN)
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastname")
                .username("username")
                .password("password")
                .position("boss")
                .build();

        //Act
        AccountEntity adminEntity = ReverseAccountConverter.convert(admin);

        //Assert
        assertTrue(adminEntity instanceof AdminEntity);
        assertEquals(admin.getAccountId(), adminEntity.getAccountId());
        assertEquals(admin.getEmail(), adminEntity.getEmail());
        assertEquals(admin.getAccountType(), adminEntity.getAccountType());
        assertEquals(admin.getUsername(), adminEntity.getUsername());
        assertEquals(admin.getPassword(), adminEntity.getPassword());
        assertEquals(admin.getFirstName(), adminEntity.getFirstName());
        assertEquals(admin.getLastName(), adminEntity.getLastName());
        assertEquals(admin.getPosition(), ((AdminEntity) adminEntity).getPosition());
    }

    @Test
    void convertDoctor_shouldConvertDoctor() {
        //Arrange
        Doctor doctor = Doctor.builder()
                .accountId(1L)
                .accountType(AccountType.DOCTOR)
                .email("user@email.com")
                .description("A nice doctor with plenty of experience")
                .photo("doctor.jpg")
                .firstName("vasile")
                .lastName("sofroni")
                .username("username")
                .password("password")
                .availableTimeSlots(new ArrayList<>())
                .specialization("teeth")
                .build();

        //Act
        AccountEntity doctorEntity = ReverseAccountConverter.convert(doctor);

        //Assert
        assertTrue(doctorEntity instanceof DoctorEntity);
        assertEquals(doctor.getAccountId(), doctorEntity.getAccountId());
        assertEquals(doctor.getPhoto(), ((DoctorEntity) doctorEntity).getPhoto());
        assertEquals(doctor.getFirstName(), doctorEntity.getFirstName());
        assertEquals(doctor.getDescription(), ((DoctorEntity) doctorEntity).getDescription());
        assertEquals(doctor.getLastName(), doctorEntity.getLastName());
        assertEquals(doctor.getEmail(), doctorEntity.getEmail());
        assertEquals(doctor.getAccountType(), doctorEntity.getAccountType());
        assertEquals(doctor.getUsername(), doctorEntity.getUsername());
        assertEquals(doctor.getPassword(), doctorEntity.getPassword());
        assertEquals(doctor.getSpecialization(), ((DoctorEntity) doctorEntity).getSpecialization());
        assertEquals(doctor.getAvailableTimeSlots(), ((DoctorEntity) doctorEntity).getAvailableTimeSlots().stream().map(TimeSlotConverter::convert).toList());
    }

}