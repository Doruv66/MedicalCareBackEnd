package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.account.GetAccountUseCaseImpl;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
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
        //Arrange
        AccountEntity accountEntity = UserEntity.builder()
                .accountId(1L)
                .username("user")
                .accountType(AccountType.USER)
                .email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        when(accountRepositoryMock.findById(1L))
                .thenReturn(Optional.ofNullable(accountEntity));

        //Act
        Account actualResult = getAccountUseCase.getAccount(1L).get();


        //Assert
        Account expectedResult = User.builder()
                .accountId(1L)
                .username("user")
                .accountType(AccountType.USER)
                .email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();

        assertEquals(actualResult, expectedResult);
    }

}