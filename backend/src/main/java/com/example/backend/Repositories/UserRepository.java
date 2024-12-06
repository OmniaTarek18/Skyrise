package com.example.backend.Repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.backend.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT * FROM user WHERE user.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
    @Query(value = "SELECT COUNT(*) > 0 FROM user WHERE user.email = :email", nativeQuery = true)
    int existsByEmail(@Param("email") String email);
}
