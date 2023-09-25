package com.medicalcare.medicalcareappointments.exception;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundAccountException extends ResponseStatusException {
    public NotFoundAccountException(String errorCode){
        super(HttpStatus.NOT_FOUND, errorCode);
    }
}
