package com.example.backend.Services;

import com.example.backend.Entities.Account;

import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Repositories.UserRepository;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

import java.util.Date;

    @Service
    public class UserServices {

        private final UserRepository userRepository ;
        @Autowired
        public UserServices(UserRepository userRepository) {
            this.userRepository = userRepository ;
        }


        public User createUser(Account account , String countryCode, String phoneNumber, String nationalId, Date dateOfBirth,
                               String firstName, String lastName, Gender gender, String passportNumber,
                               String passportIssuingCountry) {
            return  new User( account , countryCode, phoneNumber, nationalId, dateOfBirth,
                    firstName, lastName, gender, passportNumber, passportIssuingCountry);
        }

        public Integer addUser(User user){
            this.userRepository.save(user);
            return user.getAccount().getAccountId();
        }

    }
