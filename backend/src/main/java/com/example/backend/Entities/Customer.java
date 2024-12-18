package com.example.backend.Entities;

import com.example.backend.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
public class Customer {

    private String countryCode ;
    private String phoneNumber ;
    private String nationalId ;
    private LocalDate dateOfBirth ;
    private String firstName;
    private String lastName ;
    private Gender gender ;
    private String passportNumber ;
    private String passportIssuingCountry ;
    private  String email ;
    private String password;



}
