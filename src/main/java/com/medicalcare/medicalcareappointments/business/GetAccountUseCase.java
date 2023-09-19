package com.medicalcare.medicalcareappointments.business;

import com.medicalcare.medicalcareappointments.domain.Account;

import java.util.Optional;

public interface GetAccountUseCase {
    Optional<Account> getAccount(long accId);
}
