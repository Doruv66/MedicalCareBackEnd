package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.GetTop3DoctorsByRatingUseCase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetTop3DoctorsByRatingUseCaseImpl implements GetTop3DoctorsByRatingUseCase {
    private final AccountRepository accountRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public GetAccountsResponse getTop3DoctorsByRating() {
        List<Account> doctors = accountRepository.findByAccountType(AccountType.DOCTOR).stream()
                .map(AccountConverter::convert)
                .toList();
        return GetAccountsResponse.builder()
                .accounts(doctors.stream()
                        .sorted(Comparator.comparingDouble(this::calculateAverageRatingForDoctor).reversed())
                        .limit(3)
                        .collect(Collectors.toList())
                ).build();
    }

    private Double calculateAverageRatingForDoctor(Account doctor) {
        Long doctorAccountId = doctor.getAccountId();
        return reviewRepository.getAverageRatingForDoctor(doctorAccountId);
    }
}
