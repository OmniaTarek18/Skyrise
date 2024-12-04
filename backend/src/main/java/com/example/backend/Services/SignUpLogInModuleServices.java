package com.example.backend.Services;


import com.example.backend.Entities.Account;
import com.example.backend.Entities.Admin;
import com.example.backend.Entities.Customer;
import com.example.backend.Entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class SignUpLogInModuleServices {

    private final UserServices userServices;
    private final AccountServices accountServices ;
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

    public SignUpLogInModuleServices(UserServices userServices , AccountServices accountServices) {
        this.userServices = userServices;
        this.accountServices = accountServices ;
        bCryptPasswordEncoder=  new BCryptPasswordEncoder();
    }

    public boolean addCustomer(Customer customer){
        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        Account account = this.accountServices.createAccount(customer.getEmail() , encodedPassword ,false) ;
        User user = this.userServices.createUser(account, customer.getCountryCode(), customer.getPhoneNumber(), customer.getNationalId(), customer.getDateOfBirth(), customer.getFirstName(), customer.getLastName(), customer.isGender(), customer.getPassportNumber(), customer.getPassportIssuingCountry());
      return  this.userServices.addUser(user) ;
    }

    public boolean addAdmin(Admin admin){
        String encodedPassword = bCryptPasswordEncoder.encode(admin.getPassword());
        Account account = new Account(admin.getEmail() ,encodedPassword, true);
        return this.accountServices.addAccount(account) ;
    }

    public ResponseEntity<Boolean> signInChecker(String email  , String password) {

        Account account =  this.accountServices.checkEmailExistence(email) ;
        if (Objects.equals(account, null) || !bCryptPasswordEncoder.matches(password, account.getPassword())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account.isRole(), HttpStatus.OK);
    }

}
