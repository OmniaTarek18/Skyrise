package com.example.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class Customer {



    private String countryCode ;
    private String phoneNumber ;
    private String nationalId ;
    private Date dateOfBirth ;
    private String firstName;
    private String lastName ;
    private boolean gender ;
    private String passportNumber ;
    private String passportIssuingCountry ;
    private  String email ;
    private String password;



    public Customer(String countryCode, String phoneNumber, String nationalId, Date dateOfBirth, String firstName, String lastName, boolean gender, String passportNumber, String passportIssuingCountry, String email, String password) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.passportNumber = passportNumber;
        this.passportIssuingCountry = passportIssuingCountry;
        this.email = email;
        this.password = password;
    }

    public String getCountryCode() { return countryCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getNationalId() { return nationalId; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public boolean isGender() { return gender; }
    public String getPassportNumber() { return passportNumber; }
    public String getPassportIssuingCountry() { return passportIssuingCountry; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(boolean gender) { this.gender = gender; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
    public void setPassportIssuingCountry(String passportIssuingCountry) { this.passportIssuingCountry = passportIssuingCountry; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }


}
