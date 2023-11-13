package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.impl.timeslot.TimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.exception.NotFoundAccountException;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAccountUseCaseImplTest {
    @Mock
    private AccountRepository accountRepositoryMock;

    @InjectMocks
    private UpdateAccountUseCaseImpl updateAccountUseCase;

    @Test
    void updateUser_shouldUpdateUser() {
        //Arrange
        long id = 1;
        UpdatePatientRequest request = UpdatePatientRequest.builder()
                .firstName("vasile")
                .lastName("sofroni")
                .accountType(AccountType.PATIENT)
                .email("vasileSofroni9@gmail.com")
                .password("12345")
                .username("username")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .build();
        PatientEntity existingUser = PatientEntity.builder()
                .accountId(1L)
                .firstName("alex")
                .lastName("mella")
                .accountType(AccountType.PATIENT)
                .username("alex123")
                .dateOfBirth(new Timestamp(new Date(2011 - 1900, 11 - 1, 11).getTime()))
                .password("12344")
                .email("vasile@gmail.com")
                .build();

        when(accountRepositoryMock.findById(id)).thenReturn(Optional.of(existingUser));

        //Act
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);

        //Assert
        assertEquals(id, response.getId());
        assertEquals(request.getAccountType(), existingUser.getAccountType());
        assertEquals(request.getPassword(), existingUser.getPassword());
        assertEquals(request.getLastName(), existingUser.getLastName());
        assertEquals(request.getFirstName(), existingUser.getFirstName());
        assertEquals(request.getUsername(), existingUser.getUsername());
        assertEquals(request.getDateOfBirth(), existingUser.getDateOfBirth());
        assertEquals(request.getEmail(), existingUser.getEmail());
        verify(accountRepositoryMock, times(1)).findById(id);
        verify(accountRepositoryMock, times(1)).save(existingUser);
    }

    @Test
    void updateAdmin_shouldUpdateAdmin() {
        //Arrange
        long id = 1;
        UpdateAdminRequest request = UpdateAdminRequest.builder()
                .accountType(AccountType.ADMIN)
                .email("vasileSofroni9@gmail.com")
                .password("12345")
                .firstName("vasile")
                .lastName("sofroni")
                .username("username")
                .position("boss")
                .build();
        AdminEntity existingDoctor = AdminEntity.builder()
                .accountId(1L)
                .accountType(AccountType.ADMIN)
                .username("alex123")
                .firstName("vasile")
                .lastName("sofroni")
                .password("12344")
                .position("not boss")
                .email("vasile@gmail.com")
                .build();

        when(accountRepositoryMock.findById(id)).thenReturn(Optional.of(existingDoctor));

        //Act
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);

        //Assert
        assertEquals(id, response.getId());
        assertEquals(request.getAccountType(), existingDoctor.getAccountType());
        assertEquals(request.getPassword(), existingDoctor.getPassword());
        assertEquals(request.getUsername(), existingDoctor.getUsername());
        assertEquals(request.getEmail(), existingDoctor.getEmail());
        assertEquals(request.getPosition(), existingDoctor.getPosition());
        verify(accountRepositoryMock, times(1)).findById(id);
        verify(accountRepositoryMock, times(1)).save(existingDoctor);
    }

    @Test
    void updateDoctor_shouldUpdateDoctor() {
        //Arrange
        long id = 1;
        UpdateDoctorRequest request = UpdateDoctorRequest.builder()
                .accountType(AccountType.DOCTOR)
                .email("vasileSofroni9@gmail.com")
                .password("12345")
                .photo("doctor.jpg")
                .firstName("vasile")
                .lastName("sofroni")
                .description("A nice doctor with plenty of experience")
                .username("username")
                .specialization("health")
                .availableTimeSlots(new ArrayList<>())
                .build();
        DoctorEntity existingDoctor = DoctorEntity.builder()
                .accountId(1L)
                .accountType(AccountType.DOCTOR)
                .username("alex123")
                .password("12344")
                .photo("doctor.jpg")
                .description("aaaaaa")
                .firstName("vasile")
                .lastName("sofroni")
                .specialization("teeth")
                .availableTimeSlots(new ArrayList<>())
                .email("vasile@gmail.com")
                .build();

        when(accountRepositoryMock.findById(id)).thenReturn(Optional.of(existingDoctor));

        //Act
        UpdateAccountResponse response = updateAccountUseCase.updateAccount(request, id);

        //Assert
        assertEquals(id, response.getId());
        assertEquals(request.getAccountType(), existingDoctor.getAccountType());
        assertEquals(request.getPhoto(), existingDoctor.getPhoto());
        assertEquals(request.getDescription(), existingDoctor.getDescription());
        assertEquals(request.getFirstName(), existingDoctor.getFirstName());
        assertEquals(request.getLastName(), existingDoctor.getLastName());
        assertEquals(request.getPassword(), existingDoctor.getPassword());
        assertEquals(request.getUsername(), existingDoctor.getUsername());
        assertEquals(request.getEmail(), existingDoctor.getEmail());
        assertEquals(request.getSpecialization(), existingDoctor.getSpecialization());
        assertEquals(request.getAvailableTimeSlots(), existingDoctor.getAvailableTimeSlots().stream().map(TimeSlotConverter::convert).toList());
        verify(accountRepositoryMock, times(1)).findById(id);
        verify(accountRepositoryMock, times(1)).save(existingDoctor);
    }

    @Test
    void updateAccount_shouldReturnNotFoundException() {
        //Arrange
        long id = 1L;
        UpdateAccountRequest request = UpdateAccountRequest.builder()
                .accountType(AccountType.ADMIN)
                .email("vasileSofroni9@gmail.com")
                .password("12345")
                .firstName("vasile")
                .lastName("sofroni")
                .username("username")
                .build();
        when(accountRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundAccountException.class, () -> {
            updateAccountUseCase.updateAccount(request, id);
        });

        verify(accountRepositoryMock, times(1)).findById(id);
        verify(accountRepositoryMock, never()).save(any());
    }
}