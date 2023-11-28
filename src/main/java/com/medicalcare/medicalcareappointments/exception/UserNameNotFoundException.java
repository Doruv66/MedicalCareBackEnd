package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNameNotFoundException extends ResponseStatusException {
    public UserNameNotFoundException() {super(HttpStatus.NOT_FOUND, "USERNAME_NOT_FOUND");}
}
