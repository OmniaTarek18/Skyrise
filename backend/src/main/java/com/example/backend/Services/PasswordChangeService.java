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

    public boolean changePassword(String email, String newPassword) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with the provided email does not exist.");
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        return true;
    }

}

