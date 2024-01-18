package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;

public interface GetDoctorsBySpecializationUseCase {
    GetAccountsResponse getDoctorsBySpecialization(AccountType accountType, String specialization);
}
