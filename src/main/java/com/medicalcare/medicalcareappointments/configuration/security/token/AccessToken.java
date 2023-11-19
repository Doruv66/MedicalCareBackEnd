package com.medicalcare.medicalcareappointments.configuration.security.token;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Long getAccountId();

    AccountType getRole();

    boolean hasRole(String roleName);
}
