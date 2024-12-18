package com.example.backend.Repositories;

import com.example.backend.Entities.Flight;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backend.Entities.User;
import jakarta.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {


    Optional<User> findUserByUserId(Integer id);


    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.account.accountId = :accountId")
    void deleteByAccountAccountId(@Param("accountId") Integer accountId);

}
