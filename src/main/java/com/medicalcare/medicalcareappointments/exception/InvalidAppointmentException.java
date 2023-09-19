package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAppointmentException extends ResponseStatusException {
    public InvalidAppointmentException(String errorCode){
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
