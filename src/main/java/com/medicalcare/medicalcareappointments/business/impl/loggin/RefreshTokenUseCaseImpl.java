package com.medicalcare.medicalcareappointments.business.impl.loggin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicalcare.medicalcareappointments.business.RefreshTokenUseCase;
import com.medicalcare.medicalcareappointments.configuration.security.token.AccessToken;
import com.medicalcare.medicalcareappointments.configuration.security.token.AccessTokenDecoder;
import com.medicalcare.medicalcareappointments.configuration.security.token.AccessTokenEncoder;
import com.medicalcare.medicalcareappointments.configuration.security.token.exception.InvalidAccessTokenException;
import com.medicalcare.medicalcareappointments.configuration.security.token.impl.AccessTokenImpl;
import com.medicalcare.medicalcareappointments.domain.login.LoginResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    private final AccessTokenDecoder accessTokenDecoder;

    private final AccessTokenEncoder accessTokenEncoder;

    private final AccountRepository accountRepository;

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        String username = accessTokenDecoder.decode(refreshToken).getSubject();

        if(username != null) {
            AccountEntity UserDetails = accountRepository.findByUsername(username)
                    .orElseThrow();

            //check if token is valid

            String accessToken = accessTokenEncoder.encode(
                    new AccessTokenImpl(UserDetails.getUsername(), UserDetails.getAccountId(), UserDetails.getAccountType())
            );

            LoginResponse authResponse = LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);

        }

    }
}
