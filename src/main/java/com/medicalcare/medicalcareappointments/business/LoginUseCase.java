package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.login.LoginRequest;
import com.medicalcare.medicalcareappointments.domain.login.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest request);
}
