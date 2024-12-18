package com.example.backend.Services;

import com.example.backend.Entities.Account;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
@Transactional
public class AccountServicesTest {

    @Autowired
    private AccountServices accountServices;
    @Autowired
    private AccountRepository accountRepository;


    @Test
    void passwordChange() {
        Account account = accountServices.createAccount("email1", "Password123!", Role.USER);
        accountServices.addAccount(account);

        accountServices.changePassword(account.getEmail(), "Password12!");
        Assertions.assertEquals(account.getPassword() == "Password123!", false);
    }

    @Test
    void passwordReset() {
        Account account = accountServices.createAccount("email1", "Password123!", Role.USER);
        accountServices.addAccount(account);

        accountServices.resetPassword(account.getAccountId(), "Password12!");
        Assertions.assertEquals(account.getPassword() == "Password123!", false);
    }

    @Test
    void deleteAccount() {
        Account account = accountServices.createAccount("email1", "Password123!", Role.USER);
        accountServices.addAccount(account);
        Assertions.assertEquals(accountRepository.findAccountByAccountId(account.getAccountId()).isEmpty(), false);

        accountServices.deleteAccount(account.getAccountId());
        Assertions.assertEquals(accountRepository.findAccountByAccountId(account.getAccountId()).isEmpty(), true);

        Account account2 = accountServices.createAccount("email1", "Password123!", Role.ADMIN);
        accountServices.addAccount(account2);

        try{
            accountServices.deleteAccount(account2.getAccountId());
            Assertions.assertEquals(accountRepository.findAccountByAccountId(account2.getAccountId()).isEmpty(), true);
        }
        catch (Exception e){
            Assertions.assertEquals(e.getMessage(), "There is only one Admin");
        }

        Account account3 = accountServices.createAccount("email12", "Password123!", Role.ADMIN);
        accountServices.addAccount(account3);

        try{
            accountServices.deleteAccount(account2.getAccountId());
            Assertions.assertEquals(accountRepository.findAccountByAccountId(account2.getAccountId()).isEmpty(), true);
        }
        catch (Exception e){
            Assertions.assertEquals(e.getMessage(), "There is only one Admin");
        }


    }


}


