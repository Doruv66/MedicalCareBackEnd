package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.Admin;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;

public class ReverseAccountConverter {
    public ReverseAccountConverter(){}

    public static AccountEntity convert(Account acc){

        return switch (acc.getAccountType()) {
            case Admin -> {
                Admin admin = (Admin) acc;
                yield AdminEntity.builder()
                        .accountId(admin.getAccountId())
                        .accountType(admin.getAccountType())
                        .password(admin.getPassword())
                        .email(admin.getEmail())
                        .position(admin.getPosition())
                        .username(admin.getUsername())
                        .build();
            }
            case User -> {
                User user = (User) acc;
                yield UserEntity.builder()
                        .accountId(user.getAccountId())
                        .accountType(user.getAccountType())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .dateOfBirth(user.getDateOfBirth())
                        .build();
            }
            case Doctor -> {
                Doctor doctor = (Doctor) acc;
                yield DoctorEntity.builder()
                        .accountId(doctor.getAccountId())
                        .photo(doctor.getPhoto())
                        .name(doctor.getName())
                        .fname(doctor.getFname())
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
            default -> AccountEntity.builder()
                    .accountId(acc.getAccountId())
                    .email(acc.getEmail())
                    .password(acc.getPassword())
                    .username(acc.getUsername())
                    .accountType(acc.getAccountType())
                    .build();
        };
    }
}
