package com.example.Entities;

import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.Date;
    @Entity
    @Getter
    @Setter

    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int userId ;
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


    @OneToOne(cascade = CascadeType.ALL)
    Account account ;




        public User(){}

        public User(Account account , String countryCode, String phoneNumber, String nationalId, Date dateOfBirth, String firstName, String lastName, boolean gender, String passportNumber, String passportIssuingCountry) {
            this.account = account ;
            this.countryCode = countryCode;
            this.phoneNumber = phoneNumber;
            this.nationalId = nationalId;
            this.dateOfBirth = dateOfBirth;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.passportNumber = passportNumber;
            this.passportIssuingCountry = passportIssuingCountry;
        }


    }
