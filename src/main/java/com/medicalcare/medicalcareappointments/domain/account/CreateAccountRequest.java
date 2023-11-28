package com.medicalcare.medicalcareappointments.domain.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {

    @NotBlank
    private String username;

    private String firstName;

    private String lastName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;


    private AccountType accountType;
}
