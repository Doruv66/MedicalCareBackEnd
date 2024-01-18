package com.medicalcare.medicalcareappointments.persistence;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<Integer> countAccountEntitiesByAccountType(AccountType accountType);

    Page<AccountEntity> findByAccountType(AccountType accountType, Pageable pageable);
    @Query("SELECT a FROM AccountEntity a " +
            "LEFT JOIN DoctorEntity d ON a.accountId = d.accountId " +
            "WHERE a.accountType = :accountType " +
            "AND (LOWER(d.specialization) LIKE %:specialization%)")
    List<AccountEntity> findByAccountTypeAndSpecialization(@Param("accountType") AccountType accountType, @Param("specialization") String specialization);
    Optional<AccountEntity> findByEmail(String email);
    Optional<AccountEntity> findByUsername(String username);
    @Query("SELECT DISTINCT d FROM DoctorEntity d " +
            "WHERE LOWER(d.specialization) LIKE %:keyword% " +
            "OR LOWER(d.firstName) LIKE %:keyword% " +
            "OR LOWER(d.lastName) LIKE %:keyword%")
    List<AccountEntity> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT a FROM AccountEntity a " +
            "JOIN ReviewEntity r ON a.accountId = r.doctor.accountId " +
            "WHERE a.accountType = 'DOCTOR' " +
            "GROUP BY a.accountId " +
            "ORDER BY AVG(r.rating) DESC " +
            "LIMIT 3")
    List<AccountEntity> findTop3DoctorsByRating();
}
