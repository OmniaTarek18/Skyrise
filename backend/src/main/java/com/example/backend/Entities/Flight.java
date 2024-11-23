package com.example.backend.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private String source;
    
    @Column(nullable = false)
    private String destination;
    
    @Column(nullable = false)
    private LocalDate arrivalDate;
    
    @Column(nullable = false)
    private LocalDate departureDate;
    
    @Column(nullable = false)
    private LocalTime arrivalTime;

    @Column(nullable = false)
    private LocalTime departureTime;
    
    @Column(nullable = false)
    private float economyPrice;
    
    @Column(nullable = false)
    private float businessPrice;
    
    @Column(nullable = false)
    private int availableEconomySeats;
    
    @Column(nullable = false)
    private int availableBusinessSeats;
    
    private boolean isCancel;
}
