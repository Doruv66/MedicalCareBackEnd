package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.UpdateAccountRequest;
import com.medicalcare.medicalcareappointments.domain.account.UpdateAccountResponse;

public interface UpdateAccountUseCase {
    UpdateAccountResponse updateAccount(UpdateAccountRequest request, long id);
}
