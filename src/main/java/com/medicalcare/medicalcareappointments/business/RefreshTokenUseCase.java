package com.medicalcare.medicalcareappointments.business;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface RefreshTokenUseCase {
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
