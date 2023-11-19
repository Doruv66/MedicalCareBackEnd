package com.medicalcare.medicalcareappointments.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
