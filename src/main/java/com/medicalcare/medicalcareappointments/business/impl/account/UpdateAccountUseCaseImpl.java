package com.medicalcare.medicalcareappointments.business.impl.account;

import com.medicalcare.medicalcareappointments.business.UpdateAccountUseCase;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.account.*;
import com.medicalcare.medicalcareappointments.exception.NotFoundAccountException;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.AdminEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public UpdateAccountResponse updateAccount(UpdateAccountRequest request, long id) {
        Optional<AccountEntity> accountOptional = accountRepository.findById(id);
        if(accountOptional.isEmpty()){
            throw new NotFoundAccountException("NOT_FOUND_ACCOUNT");
        }

        AccountEntity account = accountOptional.get();
        updateFields(request, account);

        return UpdateAccountResponse.builder()
                .id(account.getAccountId())
                .build();
    }

    private void updateFields(UpdateAccountRequest request, AccountEntity account){
        switch (account.getAccountType()) {
            case ADMIN -> {
                UpdateAdminRequest adminRequest = (UpdateAdminRequest) request;
                AdminEntity admin = (AdminEntity) account;
                admin.setAccountType(adminRequest.getAccountType());
                admin.setUsername(adminRequest.getUsername());
                admin.setFirstName(adminRequest.getFirstName());
                admin.setLastName(adminRequest.getLastName());
                admin.setEmail(adminRequest.getEmail());
                admin.setPassword(adminRequest.getPassword());
                admin.setPosition(adminRequest.getPosition());
            }
            case DOCTOR -> {
                UpdateDoctorRequest doctorRequest = (UpdateDoctorRequest) request;
                DoctorEntity doctor = (DoctorEntity) account;
                doctor.setAccountType(doctorRequest.getAccountType());
                doctor.setUsername(doctorRequest.getUsername());
                doctor.setFirstName(doctorRequest.getFirstName());
                doctor.setLastName(doctorRequest.getLastName());
                doctor.setDescription(doctorRequest.getDescription());
                doctor.setPhoto(doctorRequest.getPhoto());
                doctor.setEmail(doctorRequest.getEmail());
                doctor.setPassword(doctorRequest.getPassword());
                doctor.setSpecialization(doctorRequest.getSpecialization());
                doctor.setAvailableTimeSlots(doctorRequest.getAvailableTimeSlots()
                        .stream()
                        .map(ReverseTimeSlotConverter::convert)
                        .toList());
            }
            case PATIENT -> {
                UpdatePatientRequest userRequest = (UpdatePatientRequest) request;
                PatientEntity user = (PatientEntity) account;
                user.setAccountType(userRequest.getAccountType());
                user.setUsername(userRequest.getUsername());
                user.setEmail(userRequest.getEmail());
                user.setFirstName(userRequest.getFirstName());
                user.setLastName(userRequest.getLastName());
                user.setDateOfBirth(userRequest.getDateOfBirth());
            }
            default -> {
                account.setAccountType(request.getAccountType());
                account.setEmail(request.getEmail());
                account.setFirstName(request.getFirstName());
                account.setLastName(request.getLastName());
                account.setPassword(request.getPassword());
                account.setUsername(request.getUsername());
            }
        }

        accountRepository.save(account);
    }
}
