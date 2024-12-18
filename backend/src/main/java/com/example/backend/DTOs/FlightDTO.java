package com.example.backend.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private float economyPrice;
    private float businessPrice;
    private int availableEconomySeats;
    private int availableBusinessSeats;

}
