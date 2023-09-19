package com.medicalcare.medicalcareappointments.persistence.impl;

import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeAccountRepositoryImpl implements AccountRepository {

    private static long NEXT_ID = 1;

    private final List<AccountEntity> savedAccounts;

    public FakeAccountRepositoryImpl(){this.savedAccounts = new ArrayList<>();
    }
    @Override
    public boolean existsById(long id) {
        return this.savedAccounts
                .stream()
                .anyMatch(accountEntity -> accountEntity.getAccountId().equals(id));
    }

    @Override
    public List<AccountEntity> findAll() {
        return Collections.unmodifiableList(this.savedAccounts);
    }

    @Override
    public AccountEntity save(AccountEntity account) {
        if(account.getAccountId() == null){
            account.setAccountId(NEXT_ID);
            NEXT_ID++;
            this.savedAccounts.add(account);
        }
        return account;
    }

    @Override
    public void deleteById(long accid) {
        this.savedAccounts.removeIf(accountEntity -> accountEntity.getAccountId().equals(accid));
    }

    @Override
    public Optional<AccountEntity> findById(long accid) {
        return this.savedAccounts.stream()
                .filter(accountEntity -> accountEntity.getAccountId().equals(accid))
                .findFirst();
    }
}
