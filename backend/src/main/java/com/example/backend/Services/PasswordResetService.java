package com.example.backend.Services;

import org.springframework.stereotype.Service;
import com.example.backend.Repositories.UserRepository;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;

    public PasswordResetService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean processResetPassword(String email){
        return userRepository.existsByEmail(email);
    }

}
