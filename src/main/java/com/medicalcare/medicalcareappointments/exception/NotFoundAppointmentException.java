package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundAppointmentException extends ResponseStatusException {
    public NotFoundAppointmentException(String errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }
}
