package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetAccountUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.AccountConverter;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAccountUseCaseImpl implements GetAccountUseCase {

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Optional<Account> getAccount(long accId) {
        return accountRepository.findById(accId).map(AccountConverter::convert);
    }
}
