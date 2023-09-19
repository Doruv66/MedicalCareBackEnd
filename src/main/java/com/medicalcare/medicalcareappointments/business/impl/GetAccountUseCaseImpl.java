package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.GetAccountUseCase;
import com.medicalcare.medicalcareappointments.domain.Account;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountUseCaseImpl implements GetAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> getAccount(long accId) {
        return accountRepository.findById(accId).map(AccountConverter::convert);
    }
}
