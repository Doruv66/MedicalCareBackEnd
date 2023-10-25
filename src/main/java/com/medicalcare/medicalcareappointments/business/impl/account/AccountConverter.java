package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.appointment.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.Admin;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;

final class AccountConverter {

    private AccountConverter(){}

    public static Account convert(AccountEntity acc){

        switch (acc.getAccountType())
        {
            case Admin:
                AdminEntity admin = (AdminEntity) acc;
                return Admin.builder()
                        .accountId(admin.getAccountId())
                        .accountType(admin.getAccountType())
                        .password(admin.getPassword())
                        .Email(admin.getEmail())
                        .position(admin.getPosition())
                        .username(admin.getUsername())
                        .build();
            case User:
                UserEntity user = (UserEntity) acc;
                return User.builder()
                        .accountId(user.getAccountId())
                        .accountType(user.getAccountType())
                        .username(user.getUsername())
                        .Email(user.getEmail())
                        .password(user.getPassword())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .dateOfBirth(user.getDateOfBirth())
                        .build();
            case Doctor:
                DoctorEntity doctor = (DoctorEntity) acc;
                return Doctor.builder()
                        .accountId(doctor.getAccountId())
                        .photo(doctor.getPhoto())
                        .name(doctor.getName())
                        .fname(doctor.getFname())
                        .description(doctor.getDescription())
                        .accountType(doctor.getAccountType())
                        .password(doctor.getPassword())
                        .Email(doctor.getEmail())
                        .username(doctor.getUsername())
                        .specialization(doctor.getSpecialization())
                        .availableTimeSlots(doctor.getAvailableTimeSlots().stream()
                                .map(TimeSlotConverter::convert)
                                .toList())
                        .build();
            default:
                return Account.builder()
                        .accountId(acc.getAccountId())
                        .Email(acc.getEmail())
                        .password(acc.getPassword())
                        .username(acc.getUsername())
                        .accountType(acc.getAccountType())
                        .build();

        }
    }
}
