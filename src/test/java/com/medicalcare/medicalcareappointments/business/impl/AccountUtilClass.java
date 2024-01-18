package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.Patient;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class AccountUtilClass {
    
    public static Patient createPatient() {
        return Patient.builder()
                .accountId(5L)
                .username("john.doe")
                .password("password123")
                .email("john.doe@example.com")
                .accountType(AccountType.PATIENT)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
    }

    public static PatientEntity createPatientEntity() {
        return PatientEntity.builder()
                .accountId(5L)
                .username("john.doe")
                .password("password123")
                .email("john.doe@example.com")
                .accountType(AccountType.PATIENT)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
    }

    public static Doctor createDoctor() {
        return Doctor.builder()
                .accountId(4L)
                .username("doctor")
                .password("12345")
                .email("doctor@gmail.com")
                .accountType(AccountType.DOCTOR)
                .specialization("Dermatology")
                .photo("doctor5")
                .firstName("Emily")
                .lastName("Anderson")
                .description("Dr. Emily Anderson is a leading Dermatologist who blends science and art in her practice. She is known for her innovative skincare solutions and her personalized approach to each patient's unique needs. Dr. Anderson's goal is to help her patients achieve not only healthy skin but also a renewed sense of confidence and well-being.")
                .availableTimeSlots(new ArrayList<>())
                .build();
    }

    public static DoctorEntity createDoctorEntity() {
        return DoctorEntity.builder()
                .accountId(4L)
                .username("doctor")
                .password("12345")
                .email("doctor@gmail.com")
                .accountType(AccountType.DOCTOR)
                .specialization("Dermatology")
                .photo("doctor5")
                .firstName("Emily")
                .lastName("Anderson")
                .description("Dr. Emily Anderson is a leading Dermatologist who blends science and art in her practice. She is known for her innovative skincare solutions and her personalized approach to each patient's unique needs. Dr. Anderson's goal is to help her patients achieve not only healthy skin but also a renewed sense of confidence and well-being.")
                .availableTimeSlots(new ArrayList<>())
                .build();
    }
}
