package com.example.backend.Admin;


import com.example.backend.Account.Account;
import com.example.backend.Account.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    private final AccountServices accountServices ;


    @Autowired
    public AdminServices(AccountServices accountServices) {
        this.accountServices = accountServices  ;
    }

    public boolean upgradeUserToAdmin(String email){
        return  this.accountServices.updateAccountFromCustomerToAdmin(email) ;
    }
}