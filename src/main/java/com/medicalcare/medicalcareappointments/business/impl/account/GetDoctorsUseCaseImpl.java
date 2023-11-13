package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetDoctorsUseCase;
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
public class GetDoctorsUseCaseImpl implements GetDoctorsUseCase {

    private AccountRepository accountRepository;

    @Transactional
    @Override
    public GetAccountsResponse getDoctors() {
        final GetAccountsResponse response = new GetAccountsResponse();
        List<Account> doctors = accountRepository.findByAccountType(AccountType.DOCTOR)
                .stream()
                .map(AccountConverter::convert)
                .toList();
        response.setAccounts(doctors);
        return response;
    }
}
