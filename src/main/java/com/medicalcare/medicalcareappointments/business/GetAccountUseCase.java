package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.GetAccountResponse;

public interface GetAccountUseCase {
    GetAccountResponse getAccount(long accId);
}
