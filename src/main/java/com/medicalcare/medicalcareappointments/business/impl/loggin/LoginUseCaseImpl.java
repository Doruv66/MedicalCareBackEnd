package com.medicalcare.medicalcareappointments.business.impl.loggin;

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
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    private final RefreshTokenEnconder refreshTokenEnconder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        AccountEntity account = accountRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(UserNameNotFoundException::new);

        if (!matchesPassword(loginRequest.getPassword(), account.getPassword())) {
            throw new PasswordsDontMatchException();
        }

        String accessToken = generateAccessToken(account);
        String refreshToken = generateRefreshToken(account);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
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

    private String generateRefreshToken(AccountEntity account){
        Long accountId = account.getAccountId();
        AccountType type = account.getAccountType();
        return refreshTokenEnconder.refreshTokenEncode(
                new AccessTokenImpl(account.getUsername(), accountId, type)
        );
    }


}
