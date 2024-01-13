package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.Doctor;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.domain.account.GetDoctorsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
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
        int pageNumber = 0;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        DoctorEntity doctorEntity1 = DoctorEntity.builder()
                .username("doctor1")
                .accountType(AccountType.DOCTOR)
                .email("doctor1@gamil.com")
                .description("A nice doctor with plenty of experience")
                .password("secret")
                .firstName("name")
                .lastName("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();
        DoctorEntity doctorEntity2 = DoctorEntity.builder()
                .username("doctor2")
                .accountType(AccountType.DOCTOR)
                .email("doctor2@gamil.com")
                .password("secret")
                .firstName("name")
                .description("A nice doctor with plenty of experience")
                .lastName("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();
        PatientEntity patientEnity = PatientEntity.builder()
                .username("user")
                .accountType(AccountType.PATIENT)
                .email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();

        Page<AccountEntity> doctorEntitiesPage = new PageImpl<>(List.of(doctorEntity1, doctorEntity2));

        when(accountRepositoryMock.findByAccountType(AccountType.DOCTOR, pageable))
                .thenReturn(doctorEntitiesPage);


        //Act
        GetDoctorsResponse actualResult = getDoctorsUseCase.getDoctors(pageNumber, pageSize);
        //Assert
        Doctor doctor1 = Doctor.builder()
                .username("doctor1")
                .accountType(AccountType.DOCTOR)
                .email("doctor1@gamil.com")
                .description("A nice doctor with plenty of experience")
                .password("secret")
                .firstName("name")
                .lastName("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();
        Doctor doctor2 = Doctor.builder()
                .username("doctor2")
                .accountType(AccountType.DOCTOR)
                .description("A nice doctor with plenty of experience")
                .email("doctor2@gamil.com")
                .password("secret")
                .firstName("name")
                .lastName("fname")
                .availableTimeSlots(new ArrayList<>())
                .specialization("neurolog")
                .build();

        GetDoctorsResponse expectedResult = GetDoctorsResponse.builder()
                .accounts(List.of(doctor1, doctor2))
                .accountsCount(2)
                .build();

        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).findByAccountType(AccountType.DOCTOR, pageable);
    }

}