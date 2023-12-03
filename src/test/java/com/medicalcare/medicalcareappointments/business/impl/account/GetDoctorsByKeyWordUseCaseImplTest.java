package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetDoctorsByKeyWordUseCaseImplTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private GetDoctorsByKeyWordUseCaseImpl getDoctorsByKeyWordUseCase;

    @Test
    void testGetDoctorsByKeyword() {
        // Mock data
        String keyword = "cardiology";

        AccountEntity doctor1 = DoctorEntity.builder()
                .accountId(1L)
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("pass123")
                .email("john@example.com")
                .accountType(AccountType.DOCTOR)
                .specialization("Cardiology")
                .availableTimeSlots(new ArrayList<>())
                .photo("cardio.jpg")
                .description("Experienced cardiologist")
                .build();

        AccountEntity doctor2 = DoctorEntity.builder()
                .accountId(2L)
                .firstName("Alice")
                .availableTimeSlots(new ArrayList<>())
                .lastName("Smith")
                .username("alicesmith")
                .password("pass456")
                .email("alice@example.com")
                .accountType(AccountType.DOCTOR)
                .specialization("Pediatrics")
                .photo("pediatrics.jpg")
                .description("Pediatrician with a caring approach")
                .build();

        List<AccountEntity> foundDoctors = new ArrayList<>();
        foundDoctors.add(doctor1);
        foundDoctors.add(doctor2);

        // Mock repository response
        when(accountRepository.findByKeyword(keyword)).thenReturn(foundDoctors);

        // Perform the method call
        GetAccountsResponse response = getDoctorsByKeyWordUseCase.getDoctorsByKeyword(keyword);

        // Assertions
        assertEquals(2, response.getAccounts().size());

        // Verify repository method call
        verify(accountRepository, times(1)).findByKeyword(keyword);
    }
}