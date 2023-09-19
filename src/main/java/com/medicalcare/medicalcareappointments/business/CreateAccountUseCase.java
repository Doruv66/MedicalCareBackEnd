package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.CreateAccountRequest;
import com.medicalcare.medicalcareappointments.domain.CreateAccountResponse;

public interface CreateAccountUseCase {
    CreateAccountResponse createAccount(CreateAccountRequest request);
}
