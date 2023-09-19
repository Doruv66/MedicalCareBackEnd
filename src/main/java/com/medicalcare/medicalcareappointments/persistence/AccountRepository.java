package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    boolean existsById(long id);

    List<AccountEntity> findAll();

    AccountEntity save(AccountEntity account);

    void deleteById(long accid);

    Optional<AccountEntity> findById(long accid);
}
