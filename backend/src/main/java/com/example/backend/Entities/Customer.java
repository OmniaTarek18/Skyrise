package com.example.backend.Entities;

import com.example.backend.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
public class Customer {

    private String countryCode ;
    private String phoneNumber ;
    private String nationalId ;
    private Date dateOfBirth ;
    private String firstName;
    private String lastName ;
    private Gender gender ;
    private String passportNumber ;
    private String passportIssuingCountry ;
    private  String email ;
    private String password;



}
