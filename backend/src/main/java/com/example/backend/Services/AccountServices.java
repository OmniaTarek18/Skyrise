package com.example.backend.Services;

import com.example.backend.Entities.Account;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServices {

    private final AccountRepository accountRepository ;

    private final BCryptPasswordEncoder bCryptPasswordEncoder ;

    @Autowired
    public AccountServices(AccountRepository accountRepository) {
        this.bCryptPasswordEncoder =new BCryptPasswordEncoder() ;
        this.accountRepository = accountRepository;
    }



    public boolean changePassword(Integer id , String password){
        Optional<Account> optionalAccount = this.accountRepository.findAccountByAccountId(id);
        if (optionalAccount.isEmpty()) {
            return false ;
        }
        Account account  = optionalAccount.get() ;
        account.setPassword(bCryptPasswordEncoder.encode(password));
        try {
            this.accountRepository.save(account);
            return true;
        } catch (Exception e) {
            return  false ;
        }
    }


}
