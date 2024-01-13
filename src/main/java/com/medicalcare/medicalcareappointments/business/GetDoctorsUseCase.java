package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.domain.account.GetDoctorsResponse;

public interface GetDoctorsUseCase {
    GetDoctorsResponse getDoctors(int pageNumber, int PageSize);
}
