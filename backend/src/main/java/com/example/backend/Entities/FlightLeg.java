package com.example.backend.Entities;

import com.example.backend.Entities.CompositeKeys.FlightLegPK;

import jakarta.persistence.*;
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
//@IdClass(FlightLegPK.class)
@Entity
public class FlightLeg {
    @Id
    @SequenceGenerator(name = "flight_leg_sequence", sequenceName = "flight_leg_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_leg_sequence")
    private Integer flightLegId;

    @ManyToOne
    @JoinColumn(name = "flight_id"  , nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @Column(nullable = false)
    private LocalTime arrivalTime;

    @Column(nullable = false)
    private LocalTime departureTime;
}
