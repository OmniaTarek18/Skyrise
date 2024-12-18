package com.example.backend.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private LocalDate arrivalDate;
    
    @Column(nullable = false)
    private LocalDate departureDate;
    
    @Column(nullable = false)
    private float economyPrice;
    
    @Column(nullable = false)
    private float businessPrice;
    
    @Column(nullable = false)
    private int availableEconomySeats;
    
    @Column(nullable = false)
    private int availableBusinessSeats;
    
    private boolean isCancel;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightLeg> flightLegs;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedback;


    public Flight(LocalDate arrivalDate, LocalDate departureDate, float economyPrice, float businessPrice, int availableEconomySeats, int availableBusinessSeats, boolean isCancel, List<FlightLeg> flightLegs) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.availableEconomySeats = availableEconomySeats;
        this.availableBusinessSeats = availableBusinessSeats;
        this.isCancel = isCancel;
        this.flightLegs = flightLegs;
    }

    public void addFlightLeg(FlightLeg flightLeg) {
        this.flightLegs.add(flightLeg);
    }
}
