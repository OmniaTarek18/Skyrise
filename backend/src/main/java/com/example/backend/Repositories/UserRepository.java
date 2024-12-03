package com.example.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
}
