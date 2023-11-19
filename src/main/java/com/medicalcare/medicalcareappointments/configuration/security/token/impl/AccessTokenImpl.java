package com.medicalcare.medicalcareappointments.configuration.security.token.impl;

import com.medicalcare.medicalcareappointments.configuration.security.token.AccessToken;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@EqualsAndHashCode
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Long accountId;
    private final AccountType role;

    public AccessTokenImpl(String subject, Long accountId, AccountType role) {
        this.subject = subject;
        this.accountId = accountId;
        this.role = role;
    }

    @Override
    public boolean hasRole(String roleName) { return this.role.toString().equals(roleName); }
}
