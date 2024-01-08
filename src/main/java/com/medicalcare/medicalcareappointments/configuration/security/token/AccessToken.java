package com.medicalcare.medicalcareappointments.configuration.security.token;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;


public interface AccessToken {
    String getSubject();

    Long getAccountId();

    AccountType getRole();

    boolean hasRole(String roleName);
}
