package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    private CreateAccountUseCaseImpl createAccountUseCase;

    @Test
    void createUser_shouldCreateUser() {
        //Arrange
        long id = 1;
        CreateUserRequest request = CreateUserRequest.builder()
                .accountType(AccountType.USER)
                .username("vasile")
                .password("1234")
                .email("vasile@gmail.com")
                .firstName("vasile")
                .lastName("sofroni")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        UserEntity user = UserEntity.builder()
                .accountId(id)
                .accountType(AccountType.USER)
                .username("vasile")
                .password("1234")
                .email("vasile@gmail.com")
                .firstName("vasile")
                .lastName("sofroni")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        when(accountRepositoryMock.save(any(AccountEntity.class))).thenReturn(user);

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
                .email("vasile@gmail.com")
                .position("position")
                .build();
        AdminEntity doctor = AdminEntity.builder()
                .accountId(id)
                .accountType(AccountType.ADMIN)
                .username("vasile")
                .password("1234")
                .email("vasile@gmail.com")
                .position("position")
                .build();
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
                .name("vasile")
                .fname("sofroni")
                .email("vasile@gmail.com")
                .specialization("teeth")
                .availableTimeSlots(new ArrayList<>())
                .build();
        DoctorEntity doctor = DoctorEntity.builder()
                .accountId(id)
                .accountType(AccountType.DOCTOR)
                .username("vasile")
                .description("A nice doctor with plenty of experience")
                .password("1234")
                .photo("doctor.jpg")
                .name("vasile")
                .fname("sofroni")
                .email("vasile@gmail.com")
                .specialization("teeth")
                .availableTimeSlots(new ArrayList<>())
                .build();
        when(accountRepositoryMock.save(any(AccountEntity.class))).thenReturn(doctor);

        //Act
        CreateAccountResponse actualResult = createAccountUseCase.createAccount(request);

        //Assert
        CreateAccountResponse expectedResult = CreateAccountResponse.builder().accountId(id).build();
        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).save(any(AccountEntity.class));
    }
}