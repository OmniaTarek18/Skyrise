package com.example.backend.Entities;

import com.example.backend.Entities.CompositeKeys.FlightLegPK;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FlightLegPK.class)
@Entity
public class FlightLeg {
    @Id
    @Column(name = "flight_leg_id", nullable = false)

    private Integer flightLegId;

    @Id
    @Column(name = "flight_id", insertable = false, updatable = false)
    private Integer flightId;

    @ManyToOne
    @JoinColumn(name = "flight_id", insertable = false, updatable = true)

    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id", nullable = false)
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id", nullable = false)
    private Airport departureAirport;
    
    @Column(nullable = false)
    private LocalTime arrivalTime;

    @Column(nullable = false)
    private LocalTime departureTime;
}
