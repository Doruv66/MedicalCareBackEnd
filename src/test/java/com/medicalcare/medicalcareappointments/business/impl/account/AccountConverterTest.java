package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.appointment.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.business.impl.appointment.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AccountConverterTest {

    @Test
    void convertUser_shouldConvertUserEntity() {
        //Arrange
        UserEntity userEntity = UserEntity.builder()
                .accountId(1L)
                .accountType(AccountType.User)
                .Email("user@email.com")
                .username("username")
                .password("password")
                .dateOfBirth(new Date(2011, 11, 11))
                .firstName("firstname")
                .lastName("lastname")
                .build();

        //Act
        Account user = AccountConverter.convert(userEntity);

        //Assert
        assertTrue(user instanceof User);
        assertEquals(userEntity.getAccountId(), user.getAccountId());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getAccountType(), user.getAccountType());
        assertEquals(userEntity.getUsername(), user.getUsername());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getFirstName(), ((User) user).getFirstName());
        assertEquals(userEntity.getLastName(), ((User) user).getLastName());
        assertEquals(userEntity.getDateOfBirth(), ((User) user).getDateOfBirth());
    }

    @Test
    void convertAdmin_shouldConvertAdminEntity() {
        //Arrange
        AdminEntity adminEntity = AdminEntity.builder()
                .accountId(1L)
                .accountType(AccountType.Admin)
                .Email("user@email.com")
                .username("username")
                .password("password")
                .position("boss")
                .build();

        //Act
        Account admin = AccountConverter.convert(adminEntity);

        //Assert
        assertTrue(admin instanceof Admin);
        assertEquals(adminEntity.getAccountId(), admin.getAccountId());
        assertEquals(adminEntity.getEmail(), admin.getEmail());
        assertEquals(adminEntity.getAccountType(), admin.getAccountType());
        assertEquals(adminEntity.getUsername(), admin.getUsername());
        assertEquals(adminEntity.getPassword(), admin.getPassword());
        assertEquals(adminEntity.getPosition(), ((Admin) admin).getPosition());
    }

    @Test
    void convertDoctor_shouldConvertDoctorEntity() {
        //Arrange
        DoctorEntity doctorEntity = DoctorEntity.builder()
                .accountId(1L)
                .accountType(AccountType.Doctor)
                .Email("user@email.com")
                .description("A nice doctor with plenty of experience")
                .photo("doctor.jpg")
                .name("vasile")
                .fname("sofroni")
                .username("username")
                .password("password")
                .availableTimeSlots(new ArrayList<>())
                .specialization("teeth")
                .build();

        //Act
        Account doctor = AccountConverter.convert(doctorEntity);

        //Assert
        assertTrue(doctor instanceof Doctor);
        assertEquals(doctorEntity.getAccountId(), doctor.getAccountId());
        assertEquals(doctorEntity.getPhoto(), ((Doctor) doctor).getPhoto());
        assertEquals(doctorEntity.getName(), ((Doctor) doctor).getName());
        assertEquals(doctorEntity.getDescription(), ((Doctor) doctor).getDescription());
        assertEquals(doctorEntity.getFname(), ((Doctor) doctor).getFname());
        assertEquals(doctorEntity.getEmail(), doctor.getEmail());
        assertEquals(doctorEntity.getAccountType(), doctor.getAccountType());
        assertEquals(doctorEntity.getUsername(), doctor.getUsername());
        assertEquals(doctorEntity.getPassword(), doctor.getPassword());
        assertEquals(doctorEntity.getSpecialization(), ((Doctor) doctor).getSpecialization());
        assertEquals(doctorEntity.getAvailableTimeSlots(), ((Doctor) doctor).getAvailableTimeSlots().stream().map(ReverseTimeSlotConverter::convert).toList());
    }
}