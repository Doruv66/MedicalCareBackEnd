package com.medicalcare.medicalcareappointments.domain.account;

import lombok.AllArgsConstructor;
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
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private AccountType accountType;
}
