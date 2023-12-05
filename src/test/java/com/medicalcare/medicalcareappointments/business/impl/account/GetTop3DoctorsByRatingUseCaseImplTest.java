package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.account.GetAccountsResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
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
class GetTop3DoctorsByRatingUseCaseImplTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private GetTop3DoctorsByRatingUseCaseImpl getTop3DoctorsByRatingUseCase;

    @Test
    void testGetTop3DoctorsByRating() {
        //Arrange
        DoctorEntity doctor1 = DoctorEntity.builder()
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

        DoctorEntity doctor2 = DoctorEntity.builder()
                .accountId(2L)
                .firstName("Alice")
                .lastName("Smith")
                .username("alicesmith")
                .password("pass456")
                .email("alice@example.com")
                .accountType(AccountType.DOCTOR)
                .specialization("Pediatrics")
                .availableTimeSlots(new ArrayList<>())
                .photo("pediatrics.jpg")
                .description("Pediatrician with a caring approach")
                .build();

        DoctorEntity doctor3 = DoctorEntity.builder()
                .accountId(3L)
                .firstName("Bob")
                .lastName("Johnson")
                .username("bobjohnson")
                .password("pass789")
                .email("bob@example.com")
                .accountType(AccountType.DOCTOR)
                .specialization("Dermatology")
                .availableTimeSlots(new ArrayList<>())
                .photo("derma.jpg")
                .description("Skincare expert")
                .build();

        List<AccountEntity> doctorsList = new ArrayList<>();
        doctorsList.add(doctor1);
        doctorsList.add(doctor2);
        doctorsList.add(doctor3);

        when(accountRepository.findTop3DoctorsByRating()).thenReturn(doctorsList);


        // Act
        GetAccountsResponse response = getTop3DoctorsByRatingUseCase.getTop3DoctorsByRating();

        // Assert
        assertEquals(3, response.getAccounts().size());
        assertEquals(1L, response.getAccounts().get(0).getAccountId());
        assertEquals(2L, response.getAccounts().get(1).getAccountId());
        assertEquals(3L, response.getAccounts().get(2).getAccountId());
        verify(accountRepository, times(1)).findTop3DoctorsByRating();
    }

}