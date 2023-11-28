package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameAlreadyExistsException extends ResponseStatusException {
    public UsernameAlreadyExistsException() { super(HttpStatus.BAD_REQUEST, "USERNAME_ALREADY_EXISTS"); }
}
