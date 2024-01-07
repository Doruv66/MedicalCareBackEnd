package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.Admin;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.Patient;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;

public class ReverseAccountConverter {
    // This constructor is intentionally left empty. It can be used for default initialization.
    private ReverseAccountConverter(){}

    public static AccountEntity convert(Account acc){

        return switch (acc.getAccountType()) {
            case ADMIN -> {
                Admin admin = (Admin) acc;
                yield AdminEntity.builder()
                        .accountId(admin.getAccountId())
                        .accountType(admin.getAccountType())
                        .firstName(admin.getFirstName())
                        .lastName(admin.getLastName())
                        .password(admin.getPassword())
                        .email(admin.getEmail())
                        .position(admin.getPosition())
                        .username(admin.getUsername())
                        .build();
            }
            case PATIENT -> {
                Patient patient = (Patient) acc;
                yield PatientEntity.builder()
                        .accountId(patient.getAccountId())
                        .accountType(patient.getAccountType())
                        .username(patient.getUsername())
                        .email(patient.getEmail())
                        .password(patient.getPassword())
                        .firstName(patient.getFirstName())
                        .lastName(patient.getLastName())
                        .dateOfBirth(patient.getDateOfBirth())
                        .build();
            }
            case DOCTOR -> {
                Doctor doctor = (Doctor) acc;
                yield DoctorEntity.builder()
                        .accountId(doctor.getAccountId())
                        .photo(doctor.getPhoto())
                        .firstName(doctor.getFirstName())
                        .lastName(doctor.getLastName())
                        .description(doctor.getDescription())
                        .accountType(doctor.getAccountType())
                        .password(doctor.getPassword())
                        .email(doctor.getEmail())
                        .username(doctor.getUsername())
                        .specialization(doctor.getSpecialization())
                        .availableTimeSlots(doctor.getAvailableTimeSlots().stream()
                                .map(ReverseTimeSlotConverter::convert)
                                .toList())
                        .build();
            }
        };
    }
}
