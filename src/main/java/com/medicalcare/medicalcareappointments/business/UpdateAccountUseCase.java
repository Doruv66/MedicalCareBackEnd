package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.UpdateAccountRequest;
import com.medicalcare.medicalcareappointments.domain.UpdateAccountResponse;

public interface UpdateAccountUseCase {
    UpdateAccountResponse updateAccount(UpdateAccountRequest request, long id);
}
