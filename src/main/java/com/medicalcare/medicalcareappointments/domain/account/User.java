package com.medicalcare.medicalcareappointments.domain.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account {
    private String firstName;
    private String lastName;
    private Timestamp dateOfBirth;
}
