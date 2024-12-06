package com.example.backend.Entities;

import com.example.backend.Enums.Gender;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String nationalId;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String passportNumber;

    @Column(nullable = false)
    private String passportIssuingCountry;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private Account account;

    public User(Account account , String countryCode, String phoneNumber, String nationalId, Date dateOfBirth, String firstName, String lastName, Gender gender, String passportNumber, String passportIssuingCountry) {
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
