package com.example.backend.Repositories;

import java.util.Optional;

import com.example.backend.Enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Optional<Account> findAccountByEmail(String email);

    Optional<Account> findAccountByAccountId(Integer id);

    boolean existsByEmail(String email);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = :role")
    int numberOfAdmins(@Param("role") Role role);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.role = :role WHERE a.email = :email")
    int updateRoleByEmail(@Param("email") String email, @Param("role") Role role);

    @Transactional
    @Modifying
    @Query("DELETE FROM Account a WHERE a.accountId = :accountId")
    int deleteAccountById(@Param("accountId") Integer accountId);

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.password = :newPassword WHERE a.email = :email")
    int updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);
}
