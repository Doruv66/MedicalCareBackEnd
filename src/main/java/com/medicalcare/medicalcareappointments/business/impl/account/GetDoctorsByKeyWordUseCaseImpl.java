package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetDoctorsByKeywordUseCase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDoctorsByKeyWordUseCaseImpl implements GetDoctorsByKeywordUseCase {
    private final AccountRepository accountRepository;
    @Transactional
    @Override
    public GetAccountsResponse getDoctorsByKeyword(String keyword) {
        List<Account> foundDoctors = accountRepository.findByKeyword(keyword).stream()
                .map(AccountConverter::convert)
                .toList();
        return GetAccountsResponse.builder()
                .accounts(foundDoctors)
                .build();
    }
}
