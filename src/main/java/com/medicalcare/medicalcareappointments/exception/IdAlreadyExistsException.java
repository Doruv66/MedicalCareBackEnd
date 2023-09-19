package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class IdAlreadyExistsException extends ResponseStatusException {
    public IdAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "ID_ALREADY_EXISTS");
    }
}
