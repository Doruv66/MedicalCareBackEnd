package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;

public interface GetDoctorsByKeywordUseCase {
    GetAccountsResponse getDoctorsByKeyword(String keyword);
}
