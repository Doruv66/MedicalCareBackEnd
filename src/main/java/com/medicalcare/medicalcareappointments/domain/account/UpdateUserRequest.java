package com.medicalcare.medicalcareappointments.domain.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest extends UpdateAccountRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Timestamp dateOfBirth;
}
