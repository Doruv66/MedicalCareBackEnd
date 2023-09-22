package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.UpdateAccountUseCase;
import com.medicalcare.medicalcareappointments.domain.UpdateAccountRequest;
import com.medicalcare.medicalcareappointments.exception.InvalidAccountException;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.domain.UpdateDoctorRequest;
import com.medicalcare.medicalcareappointments.domain.UpdateUserRequest;
import com.medicalcare.medicalcareappointments.domain.UpdateAdminRequest;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public void updateAccount(UpdateAccountRequest request, long id) {
        Optional<AccountEntity> accountOptional = accountRepository.findById(id);
        if(accountOptional.isEmpty()){
            throw new InvalidAccountException("ACCOUNT_IS_INVALID");
        }

        AccountEntity account = accountOptional.get();
        updateFields(request, account);
    }

    private void updateFields(UpdateAccountRequest request, AccountEntity account){
        switch (account.getAccountType()) {
            case Admin -> {
                UpdateAdminRequest adminRequest = (UpdateAdminRequest) request;
                AdminEntity admin = (AdminEntity) account;
                admin.setAccountType(adminRequest.getAccountType());
                admin.setEmail(adminRequest.getEmail());
                admin.setPassword(adminRequest.getPassword());
                admin.setPosition(adminRequest.getPosition());
            }
            case Doctor -> {
                UpdateDoctorRequest doctorRequest = (UpdateDoctorRequest) request;
                DoctorEntity doctor = (DoctorEntity) account;
                doctor.setAccountType(doctorRequest.getAccountType());
                doctor.setEmail(doctorRequest.getEmail());
                doctor.setPassword(doctorRequest.getPassword());
                doctor.setSpecialization(doctorRequest.getSpecialization());
                doctor.setAvailableTimeSlots(doctorRequest.getAvailableTimeSlots()
                        .stream()
                        .map(ReverseTimeSlotConverter::convert)
                        .toList());
            }
            case User -> {
                UpdateUserRequest userRequest = (UpdateUserRequest) request;
                UserEntity user = (UserEntity) account;
                user.setAccountType(userRequest.getAccountType());
                user.setEmail(userRequest.getEmail());
                user.setPassword(userRequest.getPassword());
                user.setFirstName(userRequest.getFirstName());
                user.setLastName(userRequest.getLastName());
                user.setDateOfBirth(userRequest.getDateOfBirth());
            }
            default -> {
                account.setAccountType(request.getAccountType());
                account.setEmail(request.getEmail());
                account.setPassword(request.getPassword());
                account.setUsername(request.getUsername());
            }
        }

        accountRepository.save(account);
    }
}
