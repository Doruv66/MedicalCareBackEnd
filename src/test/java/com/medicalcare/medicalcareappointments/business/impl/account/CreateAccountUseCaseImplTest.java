package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseImplTest {
    @Mock
    private AccountRepository accountRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateAccountUseCaseImpl createAccountUseCase;



    @Test
    void createUser_shouldCreateUser() {
        //Arrange
        long id = 1;
        CreatePatientRequest request = CreatePatientRequest.builder()
                .accountType(AccountType.PATIENT)
                .username("vasile")
                .password("1234")
                .email("vasile@gmail.com")
                .firstName("vasile")
                .lastName("sofroni")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        String encodedPassword = "encodedPassword123";
        PatientEntity patient = PatientEntity.builder()
                .accountId(id)
                .accountType(AccountType.PATIENT)
                .username("vasile")
                .password(encodedPassword)
                .email("vasile@gmail.com")
                .firstName("vasile")
                .lastName("sofroni")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(accountRepositoryMock.save(any(AccountEntity.class))).thenReturn(patient);

        //Act
        CreateAccountResponse actualResult = createAccountUseCase.createAccount(request);

        //Assert
        CreateAccountResponse expectedResult = CreateAccountResponse.builder().accountId(id).build();
        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).save(any(AccountEntity.class));
    }

    @Test
    void createAdmin_shouldCreateAdmin() {
        //Arrange
        long id = 1;
        CreateAdminRequest request = CreateAdminRequest.builder()
                .accountType(AccountType.ADMIN)
                .username("vasile")
                .password("1234")
                .firstName("vasile")
                .lastName("sofroni")
                .email("vasile@gmail.com")
                .position("position")
                .build();
        String encodedPassword = "encodedPassword123";
        AdminEntity doctor = AdminEntity.builder()
                .accountId(id)
                .accountType(AccountType.ADMIN)
                .username("vasile")
                .firstName("vasile")
                .lastName("sofroni")
                .password(encodedPassword)
                .email("vasile@gmail.com")
                .position("position")
                .build();
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(accountRepositoryMock.save(any(AccountEntity.class))).thenReturn(doctor);

        //Act
        CreateAccountResponse actualResult = createAccountUseCase.createAccount(request);

        //Assert
        CreateAccountResponse expectedResult = CreateAccountResponse.builder().accountId(id).build();
        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).save(any(AccountEntity.class));
    }

    @Test
    void createDoctor_shouldCreateDoctor() {
        //Arrange
        long id = 1;
        CreateDoctorRequest request = CreateDoctorRequest.builder()
                .accountType(AccountType.DOCTOR)
                .username("vasile")
                .password("1234")
                .description("A nice doctor with plenty of experience")
                .photo("doctor.jpg")
                .firstName("vasile")
                .lastName("sofroni")
                .email("vasile@gmail.com")
                .specialization("teeth")
                .availableTimeSlots(new ArrayList<>())
                .build();
        String encodedPassword = "encodedPassword123";
        DoctorEntity doctor = DoctorEntity.builder()
                .accountId(id)
                .accountType(AccountType.DOCTOR)
                .username("vasile")
                .description("A nice doctor with plenty of experience")
                .password(encodedPassword)
                .photo("doctor.jpg")
                .firstName("vasile")
                .lastName("sofroni")
                .email("vasile@gmail.com")
                .specialization("teeth")
                .availableTimeSlots(new ArrayList<>())
                .build();
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(accountRepositoryMock.save(any(AccountEntity.class))).thenReturn(doctor);

        //Act
        CreateAccountResponse actualResult = createAccountUseCase.createAccount(request);

        //Assert
        CreateAccountResponse expectedResult = CreateAccountResponse.builder().accountId(id).build();
        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).save(any(AccountEntity.class));
    }
}