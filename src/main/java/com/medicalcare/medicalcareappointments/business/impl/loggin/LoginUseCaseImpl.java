package com.medicalcare.medicalcareappointments.business.impl.loggin;

import com.medicalcare.medicalcareappointments.business.LoginUseCase;
import com.medicalcare.medicalcareappointments.configuration.security.token.AccessTokenEncoder;
import com.medicalcare.medicalcareappointments.configuration.security.token.impl.AccessTokenImpl;
import com.medicalcare.medicalcareappointments.domain.login.LoginRequest;
import com.medicalcare.medicalcareappointments.domain.login.LoginResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        AccountEntity account = accountRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NoSuchElementException("USER NOT FOUND"));

        if (!matchesPassword(loginRequest.getPassword(), account.getPassword())) {
            throw new IllegalArgumentException("PASSWORDS DON'T MATCH");
        }

        String accessToken = generateAccessToken(account);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    private String generateAccessToken(AccountEntity account){
        Long accountId = account.getAccountId();
        AccountType type = account.getAccountType();
        return accessTokenEncoder.encode(
                new AccessTokenImpl(account.getUsername(), accountId, type)
        );
    }


}
