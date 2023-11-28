package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.CreateAccountUseCase;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.exception.EmailAlreadyExistsException;
import com.medicalcare.medicalcareappointments.exception.UsernameAlreadyExistsException;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        if(accountRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        if(accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        AccountEntity savedAccount = saveNewAccount(request);

        return CreateAccountResponse.builder()
                .accountId(savedAccount.getAccountId())
                .build();
    }

    private AccountEntity saveNewAccount(CreateAccountRequest request){
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        AccountEntity newAccount = switch (request.getAccountType()) {
            case ADMIN -> {
                CreateAdminRequest adminRequest = (CreateAdminRequest) request;
                yield AdminEntity.builder()
                        .accountType(adminRequest.getAccountType())
                        .email(adminRequest.getEmail())
                        .firstName(adminRequest.getFirstName())
                        .lastName(adminRequest.getLastName())
                        .username(adminRequest.getUsername())
                        .password(encodedPassword)
                        .position(adminRequest.getPosition()).build();
            }
            case DOCTOR -> {
                CreateDoctorRequest doctorRequest = (CreateDoctorRequest) request;
                yield DoctorEntity.builder()
                        .accountType(doctorRequest.getAccountType())
                        .username(doctorRequest.getUsername())
                        .photo(doctorRequest.getPhoto())
                        .description(doctorRequest.getDescription())
                        .firstName(doctorRequest.getFirstName())
                        .lastName(doctorRequest.getLastName())
                        .email(doctorRequest.getEmail())
                        .password(encodedPassword)
                        .specialization(doctorRequest.getSpecialization())
                        .availableTimeSlots(doctorRequest.getAvailableTimeSlots()
                                .stream()
                                .map(ReverseTimeSlotConverter::convert)
                                .toList())
                        .build();
            }
            case PATIENT -> {
                CreatePatientRequest userRequest = (CreatePatientRequest) request;
                yield PatientEntity.builder()
                        .accountType(userRequest.getAccountType())
                        .email(userRequest.getEmail())
                        .username(userRequest.getUsername())
                        .password(encodedPassword)
                        .firstName(userRequest.getFirstName())
                        .lastName(userRequest.getLastName())
                        .dateOfBirth(userRequest.getDateOfBirth())
                        .build();
            }
        };
        return accountRepository.save(newAccount);
    }
}
