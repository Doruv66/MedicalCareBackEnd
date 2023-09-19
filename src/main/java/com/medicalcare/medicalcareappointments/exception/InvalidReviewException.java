package com.medicalcare.medicalcareappointments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidReviewException extends ResponseStatusException {
    public InvalidReviewException(String errorCode){
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
