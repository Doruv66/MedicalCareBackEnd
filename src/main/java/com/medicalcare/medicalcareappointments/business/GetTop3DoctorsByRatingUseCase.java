package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;

public interface GetTop3DoctorsByRatingUseCase {
    GetAccountsResponse getTop3DoctorsByRating();
}
