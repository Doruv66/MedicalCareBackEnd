package com.medicalcare.medicalcareappointments.configuration.security.token;

public interface RefreshTokenEnconder {
    String refreshTokenEncode(AccessToken accessToken);
}
