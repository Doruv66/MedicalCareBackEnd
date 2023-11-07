package com.medicalcare.medicalcareappointments.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@DiscriminatorValue("USER")
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AccountEntity {
    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;
}
