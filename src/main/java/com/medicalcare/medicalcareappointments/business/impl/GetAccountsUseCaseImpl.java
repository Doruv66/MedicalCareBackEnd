package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.GetAccountsUsecase;
import com.medicalcare.medicalcareappointments.domain.Account;
import com.medicalcare.medicalcareappointments.domain.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAccountsUseCaseImpl implements GetAccountsUsecase {

    private AccountRepository accountRepository;

    @Override
    public GetAccountsResponse getAll() {
        final GetAccountsResponse response = new GetAccountsResponse();
        List<Account> accounts = accountRepository.findAll().stream()
                .map(AccountConverter::convert)
                .toList();
        response.setAccounts(accounts);
        return response;
    }
}
