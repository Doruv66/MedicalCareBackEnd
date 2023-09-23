package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.domain.Account;
import com.medicalcare.medicalcareappointments.domain.AccountType;
import com.medicalcare.medicalcareappointments.domain.User;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAccountUseCaseImplTest {
    @Mock
    private AccountRepository accountRepositoryMock;

    @InjectMocks
    private GetAccountUseCaseImpl getAccountUseCase;

    @Test
    void getAccount_shouldReturnConvertedAccount() {
        AccountEntity accountEntity = UserEntity.builder()
                .accountId(1L)
                .username("user")
                .accountType(AccountType.User)
                .Email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Date(2011, 11, 11))
                .build();
        when(accountRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(accountEntity));

        Account actualResult = getAccountUseCase.getAccount(1L).get();

        Account expectedResult = User.builder()
                .accountId(1L)
                .username("user")
                .accountType(AccountType.User)
                .Email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Date(2011, 11, 11))
                .build();

        assertEquals(actualResult, expectedResult);
    }

}