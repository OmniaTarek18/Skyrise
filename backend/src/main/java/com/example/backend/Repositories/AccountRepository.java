package com.example.backend.Repositories;

import com.example.backend.Entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByEmail(String email) ;
    Optional<Account> findAccountByAccountId(Integer id);

    boolean existsByEmail(String email) ;

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.role = :role WHERE a.email = :email")
    int updateRoleByEmail(@Param("email") String email, @Param("role") boolean role);

}

