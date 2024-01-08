package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetTop3DoctorsByRatingUseCase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTop3DoctorsByRatingUseCaseImpl implements GetTop3DoctorsByRatingUseCase {
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public GetAccountsResponse getTop3DoctorsByRating() {
        List<Account> doctors = accountRepository.findTop3DoctorsByRating().stream()
                .map(AccountConverter::convert)
                .toList();
        return GetAccountsResponse.builder()
                .accounts(doctors)
                .build();
    }
}
