package com.medicalcare.medicalcareappointments.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account{
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
