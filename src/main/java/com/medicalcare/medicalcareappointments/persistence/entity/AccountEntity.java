package com.medicalcare.medicalcareappointments.persistence.entity;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;


@Entity
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Length(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Length(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "username")
    private String username;

    @Length(min = 2, max = 255)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Length(min = 2, max = 100)
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type",nullable = false, updatable = false)
    private AccountType accountType;
}
