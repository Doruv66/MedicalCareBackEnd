package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.account.GetAccountsUseCaseImpl;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.domain.account.User;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        UserEntity user1Enity = UserEntity.builder()
                .username("user")
                .accountType(AccountType.User)
                .Email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Date(2011, 11,11))
                .build();
        UserEntity user2Enity = UserEntity.builder()
                .username("user")
                .accountType(AccountType.User)
                .Email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Date(2011, 11,11))
                .build();

        when(accountRepositoryMock.findAll())
                .thenReturn(List.of(user1Enity, user2Enity));


        //Act
        GetAccountsResponse actualResult = getAccountsUseCase.getAll();

        //Assert
        User user1= User.builder()
                .username("user")
                .accountType(AccountType.User)
                .Email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Date(2011, 11,11))
                .build();
        User user2 = User.builder()
                .username("user")
                .accountType(AccountType.User)
                .Email("user@gmail.com")
                .password("12345")
                .firstName("user")
                .lastName("resu")
                .dateOfBirth(new Date(2011, 11,11))
                .build();

        GetAccountsResponse expectedResult = GetAccountsResponse.builder().accounts(List.of(user1, user2)).build();

        assertEquals(actualResult, expectedResult);
        verify(accountRepositoryMock).findAll();
    }


}