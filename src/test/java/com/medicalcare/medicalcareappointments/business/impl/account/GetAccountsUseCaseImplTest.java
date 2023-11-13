package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.domain.account.Patient;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAccountsUseCaseImplTest {
    @Mock
    private AccountRepository accountRepositoryMock;

    @InjectMocks
    private GetAccountsUseCaseImpl getAccountsUseCase;

    @Test
    void getAccounts_shouldReturnAllAccountsConverted() {

        //Arrange
        PatientEntity user1Enity = PatientEntity.builder()
                .username("user")
                .accountType(AccountType.PATIENT)
                .email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        PatientEntity user2Enity = PatientEntity.builder()
                .username("user")
                .accountType(AccountType.PATIENT)
                .email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();

        when(accountRepositoryMock.findAll())
                .thenReturn(List.of(user1Enity, user2Enity));


        //Act
        GetAccountsResponse actualResult = getAccountsUseCase.getAll();

        //Assert
        Patient patient1 = Patient.builder()
                .username("user")
                .accountType(AccountType.PATIENT)
                .email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        Patient patient2 = Patient.builder()
                .username("user")
                .accountType(AccountType.PATIENT)
                .email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();

        GetAccountsResponse expectedResult = GetAccountsResponse.builder().accounts(List.of(patient1, patient2)).build();

        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).findAll();
    }
}