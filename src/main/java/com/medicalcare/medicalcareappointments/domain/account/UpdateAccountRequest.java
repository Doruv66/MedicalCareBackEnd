package com.medicalcare.medicalcareappointments.domain.account;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String Email;

    private AccountType accountType;
}
