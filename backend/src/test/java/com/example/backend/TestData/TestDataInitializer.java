package com.example.backend.TestData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.example.backend.Entities.Account;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.Role;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;

import lombok.Getter;

import com.example.backend.Repositories.AccountRepository;

@Getter
public class TestDataInitializer {

        private final ReservationRepository reservationRepository;
        private final AccountRepository accountRepository;
        private final UserRepository userRepository;
        private final FlightRepository flightRepository;
        private final AirportRepository airportRepository;
        private final FlightLegRepository flightLegRepository;

        public TestDataInitializer(ReservationRepository reservationRepository, AccountRepository accountRepository,
                        UserRepository userRepository, FlightRepository flightRepository,
                        AirportRepository airportRepository,
                        FlightLegRepository flightLegRepository) {

                this.airportRepository = airportRepository;
                this.flightLegRepository = flightLegRepository;
                this.flightRepository = flightRepository;
                this.accountRepository = accountRepository;
                this.userRepository = userRepository;
                this.reservationRepository = reservationRepository;
        }

        public void generateData() {
                // Create Airports
                Airport departureAirport = createAirport("JFK", "New York", "USA", "JFK");
                Airport arrivalAirport = createAirport("LAX", "Los Angeles", "USA", "LAX");

                // Create Flight
                Flight flight = createFlight(
                                1,
                                LocalDate.of(2024, 12, 20), // Departure Date
                                LocalDate.of(2024, 12, 21), // Arrival Date
                                100.0f, // Economy Price
                                200.0f, // Business Price
                                50, // Available Economy Seats
                                10, // Available Business Seats
                                false // Is Canceled
                );

                // Create Flight Leg
                createFlightLeg(
                                arrivalAirport,
                                departureAirport,
                                LocalTime.of(12, 30), // Arrival Time
                                LocalTime.of(8, 0), // Departure Time
                                1, // Flight Leg ID
                                flight);

                // Create Account
                Account account = addAccount(1, "example@gmail.com", "securepassword", Role.USER);

                // Create User
                User user = addUser(1, account, Gender.FEMALE, "Jane", "Doe", "123456789",
                                LocalDate.of(1990, 5, 10), "US", "1234567890",
                                "A1234567", "USA");

                // Create Reservation
                addReservation(user, flight, SeatClass.ECONOMY, 1);
        }

        public void cleanDatabase() {
                accountRepository.deleteAll();
                flightRepository.deleteAll();
                flightLegRepository.deleteAll();
                airportRepository.deleteAll();
                userRepository.deleteAll();
                reservationRepository.deleteAll();
        }

        private Airport createAirport(String name, String city, String country, String code) {
                Airport airport = Airport.builder()
                                .airportName(name)
                                .airportCity(city)
                                .airportCountry(country)
                                .airportCode(code)
                                .build();
                airportRepository.save(airport);
                return airport;
        }

        private FlightLeg createFlightLeg(Airport arrival, Airport departure, LocalTime arrivalTime,
                        LocalTime departureTime, Integer flightLegId, Flight flight) {
                                
                FlightLeg flightLeg = FlightLeg.builder()
                                .arrivalAirport(arrival)
                                .departureAirport(departure)
                                .arrivalTime(arrivalTime)
                                .departureTime(departureTime)
                                .flightLegId(flightLegId)
                                .flight(flight)
                                .flightId(flight.getId())
                                .build();
                flightLegRepository.save(flightLeg);
                flight.getFlightLegs().add(flightLeg);
                flightRepository.save(flight);
                return flightLeg;
        }

        private Flight createFlight(Integer id, LocalDate date, LocalDate arrivalDate, float economyPrice,
                        float businessPrice,
                        int availableEconomySeats, int availableBusinessSeats, boolean isCancel) {
                Flight flight = Flight.builder()
                                .id(id)
                                .isCancel(isCancel)
                                .departureDate(date)
                                .arrivalDate(arrivalDate)
                                .economyPrice(economyPrice)
                                .businessPrice(businessPrice)
                                .availableEconomySeats(availableEconomySeats)
                                .availableBusinessSeats(availableBusinessSeats)
                                .flightLegs(new ArrayList<>())
                                .reservations(new ArrayList<>())
                                .build();
                flightRepository.save(flight);
                return flight;
        }

        private Account addAccount(Integer accountId, String email, String password, Role role) {
                Account account = Account.builder()
                                .accountId(accountId)
                                .email(email)
                                .password(password)
                                .role(role)
                                .build();
                accountRepository.save(account);
                return account;
        }

        private User addUser(Integer userId, Account account, Gender gender, String firstName, String lastName,
                        String nationalId,
                        LocalDate dateOfBirth, String countryCode, String phoneNumber, String passportNumber,
                        String passportIssuingCountry) {
                User user = User.builder()
                                .userId(userId)
                                .account(account)
                                .gender(gender)
                                .firstName(firstName)
                                .lastName(lastName)
                                .nationalId(nationalId)
                                .dateOfBirth(dateOfBirth)
                                .countryCode(countryCode)
                                .phoneNumber(phoneNumber)
                                .passportNumber(passportNumber)
                                .passportIssuingCountry(passportIssuingCountry)
                                .reservations(new ArrayList<>())
                                .build();
                userRepository.save(user);
                return user;
        }

        private Reservation addReservation(User user, Flight flight, SeatClass seatClass, int reservedSeats) {
                Flight managedFlight = flightRepository.findById(flight.getId()).orElseThrow(
                                () -> new IllegalArgumentException("Flight not found with id: " + flight.getId()));

                User managedUser = userRepository.findById(user.getUserId()).orElseThrow(
                        () -> new IllegalArgumentException("User not found with id: " + user.getUserId()));

                Reservation reservation = Reservation.builder()
                                .user(managedUser)
                                .flight(managedFlight)
                                .seatClass(seatClass)
                                .reservedSeats(reservedSeats)
                                .build();

                reservationRepository.save(reservation);
                managedUser.getReservations().add(reservation);
                userRepository.save(managedUser);
                managedFlight.getReservations().add(reservation);
                flightRepository.save(managedFlight);
                return reservation;
        }
}
