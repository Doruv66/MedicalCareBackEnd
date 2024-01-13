package com.medicalcare.medicalcareappointments.business.impl.loggin;

import static org.junit.jupiter.api.Assertions.*;
import com.medicalcare.medicalcareappointments.business.LoginUseCase;
import com.medicalcare.medicalcareappointments.configuration.security.token.AccessTokenEncoder;
import com.medicalcare.medicalcareappointments.configuration.security.token.RefreshTokenEnconder;
import com.medicalcare.medicalcareappointments.configuration.security.token.impl.AccessTokenImpl;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.login.LoginRequest;
import com.medicalcare.medicalcareappointments.domain.login.LoginResponse;
import com.medicalcare.medicalcareappointments.exception.PasswordsDontMatchException;
import com.medicalcare.medicalcareappointments.exception.UserNameNotFoundException;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @Mock
    private RefreshTokenEnconder refreshTokenEnconder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void login_shouldReturnLoginResponseWhenCredentialsAreValid() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("validUsername", "validPassword");
        AccountEntity accountEntity = new AccountEntity(/* Set properties for a valid account */);

        when(accountRepository.findByUsername("validUsername")).thenReturn(Optional.of(accountEntity));
        when(passwordEncoder.matches("validPassword", accountEntity.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn("validAccessToken");
        when(refreshTokenEnconder.refreshTokenEncode(any())).thenReturn("validRefreshToken");

        // Act
        LoginResponse loginResponse = loginUseCase.login(loginRequest);

        // Assert
        assertNotNull(loginResponse);
        assertEquals("validAccessToken", loginResponse.getAccessToken());
        assertEquals("validRefreshToken", loginResponse.getRefreshToken());
    }

    @Test
    void login_shouldThrowUserNameNotFoundExceptionWhenUsernameNotFound() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("nonexistentUsername", "validPassword");

        when(accountRepository.findByUsername("nonexistentUsername")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNameNotFoundException.class, () -> loginUseCase.login(loginRequest));
    }

    @Test
    void login_shouldThrowPasswordsDontMatchExceptionWhenPasswordsDoNotMatch() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("validUsername", "invalidPassword");
        AccountEntity accountEntity = new AccountEntity(/* Set properties for a valid account */);

        when(accountRepository.findByUsername("validUsername")).thenReturn(Optional.of(accountEntity));
        when(passwordEncoder.matches("invalidPassword", accountEntity.getPassword())).thenReturn(false);

        // Act & Assert
        assertThrows(PasswordsDontMatchException.class, () -> loginUseCase.login(loginRequest));
    }

}