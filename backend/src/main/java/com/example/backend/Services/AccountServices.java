package com.example.backend.Services;

import com.example.backend.Entities.Account;
import com.example.backend.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountServices {

    private final AccountRepository accountRepository ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountServices(AccountRepository accountRepository) {
        this.accountRepository = accountRepository ;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    public boolean changePassword(String email, String currentPassword, String newPassword) {


        Optional<Account> optionalAccount = this.accountRepository.findAccountByEmail(email);
        if (optionalAccount.isEmpty()) {
            throw new IllegalArgumentException("User with the provided email does not exist.");
        }
        Account account = optionalAccount.get();
        if (!this.bCryptPasswordEncoder.matches(currentPassword , account.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect.");
        }

        if (!validateNewPassword(newPassword)) {
            throw new IllegalArgumentException("New password does not meet the security requirements.");
        }

        account.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
        try {
            this.accountRepository.save(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateNewPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[@#$%^&+=!].*");
    }
}
