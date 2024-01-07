package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.timeslot.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.Admin;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.Patient;
import com.medicalcare.medicalcareappointments.persistence.entity.*;

public class AccountConverter {
    // This constructor is intentionally left empty. It can be used for default initialization
    private AccountConverter(){}

    public static Account convert(AccountEntity acc){

        return switch (acc.getAccountType()) {
            case ADMIN -> {
                AdminEntity admin = (AdminEntity) acc;
                yield Admin.builder()
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
                PatientEntity patient = (PatientEntity) acc;
                yield Patient.builder()
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
                DoctorEntity doctor = (DoctorEntity) acc;
                yield Doctor.builder()
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
                                .map(TimeSlotConverter::convert)
                                .toList())
                        .build();
            }
        };

    }

}
