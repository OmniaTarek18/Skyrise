package com.example.backend.Services;

import com.example.backend.Repositories.UserRepository;
import com.example.backend.Entities.User;
import org.springframework.stereotype.Service;


@Service
public class PasswordChangeService {

    private final UserRepository userRepository;

    public PasswordChangeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean changePassword(String email, String currentPassword, String newPassword) {


        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with the provided email does not exist.");
        }

        if (!currentPassword.equals(user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect.");
        }

        if (!validateNewPassword(newPassword)) {
            throw new IllegalArgumentException("New password does not meet the security requirements.");
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        return true;
    }

    private boolean validateNewPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[@#$%^&+=!].*");
    }
}

