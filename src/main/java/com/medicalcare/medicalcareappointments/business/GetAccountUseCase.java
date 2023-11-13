package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.GetAccountResponse;

import java.util.Optional;

public interface GetAccountUseCase {
    GetAccountResponse getAccount(long accId);
}
