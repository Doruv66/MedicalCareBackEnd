package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetAccountUseCase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountResponse;
import com.medicalcare.medicalcareappointments.exception.NotFoundAccountException;
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
    public GetAccountResponse getAccount(long accId) {
        Optional<Account>  account = accountRepository.findById(accId).map(AccountConverter::convert);
        if(account.isEmpty()) {
            throw new NotFoundAccountException("NOT_FOUND");
        }
        return GetAccountResponse.builder().account(account.get()).build();
    }
}
