package com.example.backend.Repositories;

import com.example.backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE user.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
    @Query(value = "SELECT COUNT(*) > 0 FROM user WHERE user.email = :email", nativeQuery = true)
    int existsByEmail(@Param("email") String email);
}