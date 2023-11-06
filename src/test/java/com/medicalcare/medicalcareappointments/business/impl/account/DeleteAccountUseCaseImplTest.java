package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.exception.NotFoundAccountException;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteAccountUseCaseImplTest {
    @Mock
    private AccountRepository accountRepositoryMock;

    @InjectMocks
    private DeleteAccountUseCaseImpl deleteAccountUseCase;

    @Test
    void deleteAccount_shouldDeleteTheAccount() {
        //Arrange
        long accountId = 1L;

        when(accountRepositoryMock.findById(accountId)).thenReturn(Optional.ofNullable(UserEntity.builder().username("user").accountType(AccountType.User).email("user@gmail.com").password("12345").firstName("user").lastName("resu").dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime())).build()));

        //Act
        deleteAccountUseCase.deleteAccount(accountId);

        //Assert
        verify(accountRepositoryMock, times(1)).findById(accountId);

        verify(accountRepositoryMock, times(1)).deleteById(accountId);
    }

    @Test
    void deleteAccount_shouldThrowNotFoundException() {
        //Arrange
        long accountId = 2L;

        when(accountRepositoryMock.findById(accountId)).thenReturn(Optional.empty());

        //Act and Assert

        assertThrows(NotFoundAccountException.class, () -> deleteAccountUseCase.deleteAccount(accountId));

        verify(accountRepositoryMock, times(1)).findById(accountId);

        verify(accountRepositoryMock, never()).deleteById(accountId);
    }
}