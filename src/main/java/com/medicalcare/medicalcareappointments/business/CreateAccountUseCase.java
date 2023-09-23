package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.CreateAccountRequest;
import com.medicalcare.medicalcareappointments.domain.account.CreateAccountResponse;

public interface CreateAccountUseCase {
    CreateAccountResponse createAccount(CreateAccountRequest request);
}
