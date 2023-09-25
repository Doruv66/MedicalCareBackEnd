package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.DeleteAccountUseCase;
import com.medicalcare.medicalcareappointments.exception.NotFoundAccountException;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final AccountRepository accountRepository;
    @Override
    public void deleteAccount(long accId) {
        if(accountRepository.findById(accId).isEmpty()){
            throw new NotFoundAccountException("ACCOUNT_NOT_FOUND");
        }
        this.accountRepository.deleteById(accId);
    }
}
