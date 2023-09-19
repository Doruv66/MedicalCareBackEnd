package com.medicalcare.medicalcareappointments.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class UserEntity extends AccountEntity{
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
