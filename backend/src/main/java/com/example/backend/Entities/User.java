package com.example.backend.Entities;

import java.util.List;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId ;
    @Column(nullable = false)
    private String countryCode ;
    @Column(nullable = false)
    private String phoneNumber ;
    @Column(nullable = false , unique = true)
    private String nationalId ;
    @Column(nullable = false)
    private Date dateOfBirth ;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName ;
    @Column(nullable = false)
    private boolean gender ;
    @Column(nullable = false)
    private String passportNumber ;
    @Column(nullable = false)
    private String passportIssuingCountry ;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToOne(cascade = CascadeType.ALL)
    Account account ;

    public User(Account account, String countryCode, String phoneNumber, String nationalId, Date dateOfBirth, String firstName, String lastName, boolean gender, String passportNumber, String passportIssuingCountry) {
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.passportNumber = passportNumber;
        this.passportIssuingCountry = passportIssuingCountry;
        this.account = account;
    }
}
