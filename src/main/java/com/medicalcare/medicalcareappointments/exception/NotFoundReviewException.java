package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundReviewException extends ResponseStatusException {
    public NotFoundReviewException(String errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }
}
