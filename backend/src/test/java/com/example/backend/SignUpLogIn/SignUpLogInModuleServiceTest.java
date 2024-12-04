package com.example.backend.SignUpLogIn;


import com.example.Entities.Account;
import com.example.Entities.Customer;
import com.example.Entities.User;
import com.example.Services.AccountServices;
import com.example.Services.SignUpLogInModuleServices;
import com.example.Services.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SignUpLogInModuleServiceTest {


    @Mock
    private UserServices userServices;
    @Mock
    private AccountServices accountServices ;
    @InjectMocks
    private SignUpLogInModuleServices signUpLogInModuleServices ;

//addCustomer() doesn't check the validity of data, all what it does is to delegate the data into the RDBMS and the database checks the validity of data
    @Test
    void testAddCustomer_shouldReturnTrue() {
        Date dNow = new Date( );
        Customer customer = new Customer("123", "010", "1", dNow, "abc", "db", true, "123", "1", "test@example.com", "password123");
        Account account = new Account(customer.getEmail(), customer.getPassword(), false);
        User user = new User(account, customer.getCountryCode(), customer.getPhoneNumber(), customer.getNationalId(),
                customer.getDateOfBirth(), customer.getFirstName(), customer.getLastName(), customer.isGender(),
                customer.getPassportNumber(), customer.getPassportIssuingCountry());

        when(accountServices.createAccount(anyString(), anyString(), eq(false))).thenReturn(account);
        when(userServices.createUser(eq(account), anyString(), anyString(), anyString(), any(), anyString(), anyString(),
                anyBoolean(), any(), anyString())).thenReturn(user);
        when(userServices.addUser(user)).thenReturn(true) ;

        boolean result = signUpLogInModuleServices.addCustomer(customer);
        assertTrue(result) ;
    }








}
