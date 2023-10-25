package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDoctorsUseCaseImplTest {

    @Mock
    private AccountRepository accountRepositoryMock;

    @InjectMocks
    private GetDoctorsUseCaseImpl getDoctorsUseCase;

    @Test
    void getDoctors_shouldReturnAllDoctorsConverted() {

        //Arrange
        DoctorEntity doctorEntity1 = DoctorEntity.builder()
                .username("doctor1")
                .accountType(AccountType.Doctor)
                .Email("doctor1@gamil.com")
                .description("A nice doctor with plenty of experience")
                .password("secret")
                .name("name")
                .fname("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();
        DoctorEntity doctorEntity2 = DoctorEntity.builder()
                .username("doctor2")
                .accountType(AccountType.Doctor)
                .Email("doctor2@gamil.com")
                .password("secret")
                .name("name")
                .description("A nice doctor with plenty of experience")
                .fname("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();
        UserEntity userEnity = UserEntity.builder()
                .username("user")
                .accountType(AccountType.User)
                .Email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Date(2011, 11,11))
                .build();

        when(accountRepositoryMock.findAll())
                .thenReturn(List.of(doctorEntity1, doctorEntity2, userEnity));

        //Act
        GetAccountsResponse actualResult = getDoctorsUseCase.getDoctors();

        //Assert
        Doctor doctor1 = Doctor.builder()
                .username("doctor1")
                .accountType(AccountType.Doctor)
                .Email("doctor1@gamil.com")
                .description("A nice doctor with plenty of experience")
                .password("secret")
                .name("name")
                .fname("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();
        Doctor doctor2 = Doctor.builder()
                .username("doctor2")
                .accountType(AccountType.Doctor)
                .description("A nice doctor with plenty of experience")
                .Email("doctor2@gamil.com")
                .password("secret")
                .name("name")
                .fname("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();

        GetAccountsResponse expectedResult = GetAccountsResponse.builder().accounts(List.of(doctor1, doctor2)).build();
        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).findAll();
    }

}