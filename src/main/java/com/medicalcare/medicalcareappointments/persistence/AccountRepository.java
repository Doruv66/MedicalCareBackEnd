package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByAccountType(AccountType accountType);
    Optional<AccountEntity> findByEmail(String email);
    Optional<AccountEntity> findByUsername(String username);
    @Query("SELECT DISTINCT d FROM DoctorEntity d " +
            "WHERE LOWER(d.specialization) LIKE %:keyword% " +
            "OR LOWER(d.firstName) LIKE %:keyword% " +
            "OR LOWER(d.lastName) LIKE %:keyword%")
    List<AccountEntity> findByKeyword(@Param("keyword") String keyword);
}
