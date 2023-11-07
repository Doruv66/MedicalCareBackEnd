package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundTimeSlotException extends ResponseStatusException {
    public NotFoundTimeSlotException(String errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }
}
