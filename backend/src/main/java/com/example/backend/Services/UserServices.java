package com.example.backend.Services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.backend.Entities.Account;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Repositories.UserRepository;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(Account account, String countryCode, String phoneNumber, String nationalId, LocalDate dateOfBirth,
            String firstName, String lastName, Gender gender, String passportNumber,
            String passportIssuingCountry) {
        User user = User.builder().account(account).countryCode(countryCode).phoneNumber(phoneNumber)
                .nationalId(nationalId).dateOfBirth(dateOfBirth).firstName(firstName).lastName(lastName).gender(gender)
                .passportNumber(passportNumber).passportIssuingCountry(passportIssuingCountry).build();
        return user;
    }

    public Integer addUser(User user) {
        try {
            this.userRepository.save(user);
            return user.getAccount().getAccountId();
        } catch (Exception e) {
            return null;
        }
    }

}
