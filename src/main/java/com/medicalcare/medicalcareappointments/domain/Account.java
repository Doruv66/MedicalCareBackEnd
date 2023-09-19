package com.medicalcare.medicalcareappointments.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long accountId;
    private String username;
    private String password;
    private String Email;
    private AccountType accountType;
}
