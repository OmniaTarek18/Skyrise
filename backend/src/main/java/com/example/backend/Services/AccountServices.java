package com.example.backend.Services;

import com.example.backend.Entities.Account;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServices {

    private final AccountRepository accountRepository ;

    @Autowired
    public AccountServices(AccountRepository accountRepository){
        this.accountRepository = accountRepository ;
    }




    public Account createAccount(String email , String password , Role role){
        return new Account(email , password , role) ;
    }

    public Integer addAccount(Account account) {
            this.accountRepository.save(account);
            return account.getAccountId();
    }

    public Account checkEmailExistence(String email){
        Optional<Account>  optionalAccount = this.accountRepository.findAccountByEmail(email);
        return optionalAccount.orElse(null);
    }

    public boolean updateAccountFromCustomerToAdmin(String email){
        int flag  =  this.accountRepository.updateRoleByEmail(email , true) ;
        return flag == 1;
    }
}
