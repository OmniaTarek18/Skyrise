package com.example.backend.Repositories;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> f8c49ae (Add insights page)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.backend.Entities.User;

<<<<<<< HEAD
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

=======
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    
>>>>>>> f8c49ae (Add insights page)
}
