package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.timeslot.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.Admin;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.persistence.entity.*;

public class AccountConverter {

    private AccountConverter(){}

    public static Account convert(AccountEntity acc){

        return switch (acc.getAccountType()) {
            case Admin -> {
                AdminEntity admin = (AdminEntity) acc;
                yield Admin.builder()
                        .accountId(admin.getAccountId())
                        .accountType(admin.getAccountType())
                        .password(admin.getPassword())
                        .email(admin.getEmail())
                        .position(admin.getPosition())
                        .username(admin.getUsername())
                        .build();
            }
            case User -> {
                UserEntity user = (UserEntity) acc;
                yield User.builder()
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
                DoctorEntity doctor = (DoctorEntity) acc;
                yield Doctor.builder()
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
                                .map(TimeSlotConverter::convert)
                                .toList())
                        .build();
            }
            default -> Account.builder()
                    .accountId(acc.getAccountId())
                    .email(acc.getEmail())
                    .password(acc.getPassword())
                    .username(acc.getUsername())
                    .accountType(acc.getAccountType())
                    .build();
        };

    }

}
