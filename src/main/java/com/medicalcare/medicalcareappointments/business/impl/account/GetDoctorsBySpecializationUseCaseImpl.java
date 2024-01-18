package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetDoctorsBySpecializationUseCase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDoctorsBySpecializationUseCaseImpl implements GetDoctorsBySpecializationUseCase {

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public GetAccountsResponse getDoctorsBySpecialization(AccountType accountType, String specialization) {
        return GetAccountsResponse.builder()
                .accounts(accountRepository.findByAccountTypeAndSpecialization(accountType, specialization)
                        .stream()
                        .map(AccountConverter::convert)
                        .toList())
                .build();
    }
}
