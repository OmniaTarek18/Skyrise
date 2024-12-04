package com.example.backend;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.User;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;

import lombok.Getter;

@Getter
public class HelperData {

        private final ReservationRepository reservationRepository;
        private final UserRepository userRepository;
        private final FlightRepository flightRepository;
        private final AirportRepository airportRepository;
        private final FlightLegRepository flightLegRepository;

        public HelperData(
                        AirportRepository airportRepository, FlightRepository flightRepository,
                        FlightLegRepository flightLegRepository, UserRepository userRepository,
                        ReservationRepository reservationRepository) {

                this.airportRepository = airportRepository;
                this.flightRepository = flightRepository;
                this.flightLegRepository = flightLegRepository;
                this.userRepository = userRepository;
                this.reservationRepository = reservationRepository;

        }

        public void dataGeneration() {

                List<Airport> airports = List.of(
                                createAirport("JFK International Airport", "USA", "New York", "JFK", 40.6413, -73.7781),
                                createAirport("Los Angeles International Airport", "USA", "Los Angeles", "LAX", 33.9416,
                                                -118.4085),
                                createAirport("Heathrow Airport", "UK", "London", "LHR", 51.4700, -0.4543),
                                createAirport("LAX International Airport", "USA", "Los Angeles", "LAX", 33.9416,
                                                -118.4085));
                airportRepository.saveAll(airports);

                LocalDate[] date = {
                                LocalDate.parse("2024-10-12"), LocalDate.parse("2024-09-12"),
                                LocalDate.parse("2024-08-12"), LocalDate.parse("2024-10-11"),
                                LocalDate.parse("2025-10-12"), LocalDate.parse("2024-10-12") };

                for (int i = 1; i < 4; i++) {
                        User user = new User();

                        Flight flight = Flight.builder()
                                        .departureDate(date[i - 1])
                                        .arrivalDate(date[i])
                                        .flightLegs(new ArrayList<>())
                                        .economyPrice(100.0f)
                                        .businessPrice(200.0f)
                                        .availableEconomySeats(100)
                                        .availableBusinessSeats(50)
                                        .isCancel(false)
                                        .build();
                        flightRepository.save(flight);

                        FlightLeg flightLeg = FlightLeg.builder()
                                        .flightLegId(1)
                                        .flightId(i)
                                        .flight(flight)
                                        .departureAirport(airports.get(i - 1))
                                        .arrivalAirport(airports.get(i))
                                        .departureTime(LocalTime.of(10, 30))
                                        .arrivalTime(LocalTime.of(12, 30))
                                        .build();

                        flightLegRepository.save(flightLeg);

                        flight.getFlightLegs().add(flightLeg);
                        flightRepository.save(flight);

                        Reservation reservation = new Reservation();
                        reservation.setUser(user);
                        reservation.setFlight(flight);
                        reservation.setSeatClass(SeatClass.ECONOMY);
                        reservation.setReservedSeats(2);

                        user.setReservations(List.of(reservation));
                        userRepository.save(user);

                        reservationRepository.save(reservation);
                }
        }

        private Airport createAirport(String name, String country, String city, String code, double latitude,
                        double longitude) {
                return Airport.builder()
                                .airportName(name)
                                .airportCountry(country)
                                .airportCity(city)
                                .airportCode(code)
                                .latitude(latitude)
                                .longitude(longitude)
                                .build();
        }
}
