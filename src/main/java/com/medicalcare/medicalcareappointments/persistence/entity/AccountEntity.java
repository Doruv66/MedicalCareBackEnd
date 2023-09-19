package com.medicalcare.medicalcareappointments.persistence.entity;

import com.medicalcare.medicalcareappointments.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    private Long accountId;
    private String username;
    private String password;
    private String Email;
    private AccountType accountType;
}
