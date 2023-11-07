package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.CreateAccountUseCase;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        AccountEntity savedAccount = saveNewAccount(request);

        return CreateAccountResponse.builder()
                .accountId(savedAccount.getAccountId())
                .build();
    }

    private AccountEntity saveNewAccount(CreateAccountRequest request){
        AccountEntity newAccount = switch (request.getAccountType()) {
            case Admin -> {
                CreateAdminRequest adminRequest = (CreateAdminRequest) request;
                yield AdminEntity.builder()
                        .accountType(adminRequest.getAccountType())
                        .email(adminRequest.getEmail())
                        .username(adminRequest.getUsername())
                        .password(adminRequest.getPassword())
                        .position(adminRequest.getPosition()).build();
            }
            case Doctor -> {
                CreateDoctorRequest doctorRequest = (CreateDoctorRequest) request;
                yield DoctorEntity.builder()
                        .accountType(doctorRequest.getAccountType())
                        .username(doctorRequest.getUsername())
                        .photo(doctorRequest.getPhoto())
                        .description(doctorRequest.getDescription())
                        .name(doctorRequest.getName())
                        .fname(doctorRequest.getFname())
                        .email(doctorRequest.getEmail())
                        .password(doctorRequest.getPassword())
                        .specialization(doctorRequest.getSpecialization())
                        .availableTimeSlots(doctorRequest.getAvailableTimeSlots()
                                .stream()
                                .map(ReverseTimeSlotConverter::convert)
                                .toList())
                        .build();
            }
            case User -> {
                CreateUserRequest userRequest = (CreateUserRequest) request;
                yield UserEntity.builder()
                        .accountType(userRequest.getAccountType())
                        .email(userRequest.getEmail())
                        .username(userRequest.getUsername())
                        .password(userRequest.getPassword())
                        .firstName(userRequest.getFirstName())
                        .lastName(userRequest.getLastName())
                        .dateOfBirth(userRequest.getDateOfBirth())
                        .build();
            }
        };
        return accountRepository.save(newAccount);
    }
}
