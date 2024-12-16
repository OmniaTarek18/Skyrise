package com.example.backend.Entities;

import com.example.backend.Enums.Gender;
import com.example.backend.Enums.MealSpecification;
import com.example.backend.Enums.SpecialNeeds;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String nationalId;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

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

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reservation_user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "reservation_flight_id", referencedColumnName = "flight_id")
    })
    private Reservation reservation;

    private SpecialNeeds specialNeeds;
    private MealSpecification measlSpecification;
}
