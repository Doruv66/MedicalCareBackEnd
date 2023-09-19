package com.medicalcare.medicalcareappointments.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @NotBlank
    private String password;

    @NotBlank
    private String Email;


    private AccountType accountType;
}
