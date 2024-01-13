package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.LoginUseCase;
import com.medicalcare.medicalcareappointments.business.RefreshTokenUseCase;
import com.medicalcare.medicalcareappointments.domain.login.LoginRequest;
import com.medicalcare.medicalcareappointments.domain.login.LoginResponse;
import com.medicalcare.medicalcareappointments.exception.PasswordsDontMatchException;
import com.medicalcare.medicalcareappointments.exception.UserNameNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/tokens")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            LoginResponse loginResponse = loginUseCase.login(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
        } catch (PasswordsDontMatchException | UserNameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        refreshTokenUseCase.refreshToken(request, response);
    }
}
