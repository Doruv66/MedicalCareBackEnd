package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.UpdateAccountRequest;

public interface UpdateAccountUseCase {
    void updateAccount(UpdateAccountRequest request, long id);
}
