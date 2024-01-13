package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetDoctorsUseCase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.domain.account.GetDoctorsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDoctorsUseCaseImpl implements GetDoctorsUseCase {

    private AccountRepository accountRepository;

    @Transactional
    @Override
    public GetDoctorsResponse getDoctors(int pageNumber, int pageSize) {


        // Create a Pageable object for pagination
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<AccountEntity> doctorEntitiesPage = accountRepository.findByAccountType(AccountType.DOCTOR, pageable);
        //add the page numbers to be returned with the accounts
        doctorEntitiesPage.getTotalPages();
        // Convert the content of the page to a list of Account objects
        List<Account> doctors = doctorEntitiesPage.getContent()
                .stream()
                .map(AccountConverter::convert)
                .toList();


        return GetDoctorsResponse.builder()
                .accounts(doctors)
                .accountsCount(doctorEntitiesPage.getTotalElements())
                .build();
    }
}
