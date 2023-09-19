package com.medicalcare.medicalcareappointments.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest {
    private Long accountId;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String Email;

    private AccountType accountType;
}
