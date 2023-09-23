package com.medicalcare.medicalcareappointments.domain.account;

import com.medicalcare.medicalcareappointments.domain.account.Account;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
