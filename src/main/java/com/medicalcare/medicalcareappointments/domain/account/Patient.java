package com.medicalcare.medicalcareappointments.domain.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends Account {
    private Timestamp dateOfBirth;
}
