package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.DeleteAccountUseCase;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final AccountRepository accountRepository;
    @Override
    public void deleteAccount(long accId) {
        this.accountRepository.deleteById(accId);
    }
}
