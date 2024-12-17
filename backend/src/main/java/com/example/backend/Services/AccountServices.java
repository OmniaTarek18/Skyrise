package com.example.backend.Services;

import com.example.backend.Entities.Account;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.AccountRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServices {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServices(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public boolean resetPassword(Integer id, String password) {
        Optional<Account> optionalAccount = this.accountRepository.findAccountByAccountId(id);
        if (optionalAccount.isEmpty()) {
            return false;
        }
        Account account = optionalAccount.get();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        System.out.println(account.getEmail());
        System.out.println(encodedPassword);
        account.setPassword(encodedPassword);
        try {
            this.accountRepository.save(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changePassword(String email, String newPassword) {

        Optional<Account> optionalAccount = this.accountRepository.findAccountByEmail(email);
        if (optionalAccount.isEmpty()) {
            throw new IllegalArgumentException("User with the provided email does not exist.");
        }
        Account account = optionalAccount.get();

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

    public Account createAccount(String email, String password, Role role) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setRole(role);
        return account;
    }

    public Integer addAccount(Account account) {
        try {
            this.accountRepository.save(account);
            return account.getAccountId();
        } catch (Exception e) {
            return null;
        }
    }

    public Account checkEmailExistence(String email) {
        Optional<Account> optionalAccount = this.accountRepository.findAccountByEmail(email);
        return optionalAccount.orElse(null);
    }

    public boolean updateAccountFromCustomerToAdmin(String email) {
        int flag = this.accountRepository.updateRoleByEmail(email, true);
        return flag == 1;
    }

    public boolean deleteAccount(Integer accountId) {
        Optional<Account> optionalAccount = this.accountRepository.findAccountByAccountId(accountId);
        if (optionalAccount.isEmpty()) {
            throw new IllegalArgumentException("User with the provided email does not exist.");
        }
        Account account = optionalAccount.get();
        if (account.getRole() == Role.USER){
            this.accountRepository.deleteAccountById(accountId);
        }
        else if(this.accountRepository.numberOfAdmins(Role.ADMIN) > 1){
            this.accountRepository.deleteAccountById(accountId);
        }
        else{
            throw new IllegalArgumentException("There is only one Admin");
        }
        return true;
    }

}