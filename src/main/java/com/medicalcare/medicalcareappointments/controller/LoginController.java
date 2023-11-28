package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.LoginUseCase;
import com.medicalcare.medicalcareappointments.domain.login.LoginRequest;
import com.medicalcare.medicalcareappointments.domain.login.LoginResponse;
import com.medicalcare.medicalcareappointments.exception.EmailAlreadyExistsException;
import com.medicalcare.medicalcareappointments.exception.PasswordsDontMatchException;
import com.medicalcare.medicalcareappointments.exception.UserNameNotFoundException;
import com.medicalcare.medicalcareappointments.exception.UsernameAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            LoginResponse loginResponse = loginUseCase.login(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
        } catch (PasswordsDontMatchException | UserNameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }
}
