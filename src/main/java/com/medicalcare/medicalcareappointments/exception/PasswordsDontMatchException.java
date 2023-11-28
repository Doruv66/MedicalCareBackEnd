package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordsDontMatchException extends ResponseStatusException {
    public PasswordsDontMatchException() {super(HttpStatus.BAD_REQUEST, "WRONG_PASSWORD");}
}
