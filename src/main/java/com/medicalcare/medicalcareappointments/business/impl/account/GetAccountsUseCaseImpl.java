package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetAccountsUsecase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAccountsUseCaseImpl implements GetAccountsUsecase {

    private AccountRepository accountRepository;

    @Transactional
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
