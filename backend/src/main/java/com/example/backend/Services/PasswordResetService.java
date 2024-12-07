package com.example.backend.Services;

import org.springframework.stereotype.Service;
import com.example.backend.Repositories.AccountRepository;

@Service
public class PasswordResetService {

    private final AccountRepository accountRepository;

    public PasswordResetService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public boolean processResetPassword(String email){
        return accountRepository.existsByEmail(email);
    }

}
