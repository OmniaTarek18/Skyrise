package com.example.backend.Specifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.*;
import com.example.backend.Enums.FlightType;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.Role;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Repositories.AccountRepository;
import com.example.backend.Repositories.AirportRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@DataJpaTest
public class ReservationSpecificationsTest {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightLegRepository flightLegRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AirportRepository airportRepository;

    private Airport[] airports;
    private Flight[] flights;
    private User user;

    @BeforeEach
    void setup() {
        cleanDatabase();
        generateData();
    }

    @Test
    void testContainsId1Correctence() {
        // given
        int expectedNumber = 6;
        // when
        Specification<Reservation> spec = ReservationSpecifications.containsId("userId", user.getUserId());
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testContainsId2Correctence() {
        // given
        int expectedNumber = 1;
        // when
        Specification<Reservation> spec = ReservationSpecifications.containsId("flightId", flights[1].getId());
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testContainsLocationCorrectence1() {
        // given
        int expectedNumber = 6;
        // when
        Specification<Reservation> spec = ReservationSpecifications.containsLocation("departureAirport", "City1");
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testContainsLocationCorrectence2() {
        // given
        int expectedNumber = 6;
        // when
        Specification<Reservation> spec = ReservationSpecifications.containsLocation("arrivalAirport", "City2");
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testContainsDateCorrectence2() {
        // given
        int expectedNumber = 2;
        // when
        Specification<Reservation> spec = ReservationSpecifications.containsDate("departureDate",
                LocalDate.of(2024, 10, 15));
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testContainsDateCorrectence1() {
        // given
        int expectedNumber = 2;
        // when
        Specification<Reservation> spec = ReservationSpecifications.containsDate("arrivalDate",
                LocalDate.of(2024, 10, 22));
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testBeforeDate() {
        // given
        int expectedNumber = 1;
        // when
        Specification<Reservation> spec = ReservationSpecifications.beforeDate("arrivalDate",
                LocalDate.of(2024, 10, 22));
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testAfterOrEqualDate() {
        // given
        int expectedNumber = 5;
        // when
        Specification<Reservation> spec = ReservationSpecifications.afterOrEqualDate("arrivalDate",
                LocalDate.of(2024, 10, 22));
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(expectedNumber, list.size());
    }

    @Test
    void testSortedByFlightId() {
        // given
        LocalDate arrival = LocalDate.of(2024, 10, 29);
        LocalDate departure = LocalDate.of(2024, 10, 15);
        // when
        Specification<Reservation> spec = ReservationSpecifications.sortedByFlightId("desc");
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(arrival, list.get(0).getFlight().getArrivalDate());
        Assertions.assertEquals(departure, list.get(0).getFlight().getDepartureDate());
    }

    @Test
    void testSortedByDate() {
        // given
        LocalDate first = LocalDate.of(2024, 10, 4);
        // when
        Specification<Reservation> spec = ReservationSpecifications.sortedByDate("departureDate", "asc");
        List<Reservation> list = reservationRepository.findAll(spec);
        // then
        Assertions.assertEquals(first, list.get(0).getFlight().getDepartureDate());
    }

    private Flight createFlights(LocalDate date, LocalDate arrivalDate, float economyPrice, float businessPrice,
            int availableEconomySeats, int availableBusinessSeats, boolean isCancel) {
        Flight flight = Flight.builder()
                .isCancel(isCancel)
                .departureDate(date)
                .arrivalDate(arrivalDate)
                .economyPrice(economyPrice)
                .businessPrice(businessPrice)
                .availableEconomySeats(availableEconomySeats)
                .availableBusinessSeats(availableBusinessSeats)
                .build();
        flightRepository.save(flight);
        return flight;
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
                .build();
        flightLegRepository.save(flightLeg);
        return flightLeg;
    }

    private void cleanDatabase() {
        flightRepository.deleteAll();
        flightLegRepository.deleteAll();
        airportRepository.deleteAll();
    }

    private User createUser(Account account) {
        User user = User.builder()
                .account(account)
                .gender(Gender.FEMALE)
                .firstName("firstName")
                .lastName("lastName")
                .nationalId("nationalId")
                .dateOfBirth(LocalDate.of(2024, 12, 10))
                .countryCode("CountryCode")
                .phoneNumber("phoneNumber")
                .passportNumber("PassportNumber")
                .passportIssuingCountry("passportIssuingCountry")
                .build();
        userRepository.save(user);
        return user;
    }

    private Account createAccount() {
        Account account = Account.builder()
                .email("example@gmail.com")
                .password("password")
                .role(Role.USER)
                .build();
        accountRepository.save(account);
        return account;
    }

    private Reservation createReservation(User user, Flight flight) {
        Reservation reservation = Reservation.builder()
                .user(user)
                .flight(flight)
                .seatClass(SeatClass.ECONOMY)
                .reservedSeats(2)
                .build();
        reservationRepository.save(reservation);
        return reservation;
    }

    private void generateData() {
        user = createUser(createAccount());
        // create 6 airports
        airports = new Airport[5];
        for (int i = 1; i < 5; i++)
            airports[i] = createAirport("Airport" + i, "City" + i, "Country" + i, "code" + i);

        flights = new Flight[7];
        LocalDate[] departureDates = {
                LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 4), LocalDate.of(2024, 10, 10),
                LocalDate.of(2024, 10, 12), LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 15) };
        LocalDate[] arrivalDates = {
                LocalDate.of(2024, 10, 18), LocalDate.of(2024, 10, 24), LocalDate.of(2024, 10, 22),
                LocalDate.of(2024, 10, 22), LocalDate.of(2024, 10, 23), LocalDate.of(2024, 10, 29)
        };

        // create 5 flights
        for (int i = 1; i < 7; i++)
            flights[i] = createFlights(departureDates[i - 1], arrivalDates[i - 1], 1500, 2000, 5, 13, false);
        // create flightlegs
        // 1 leg for each flight
        for (int i = 1; i < 7; i++) {
            createFlightLeg(airports[2], airports[1], LocalTime.of(12, 30), LocalTime.of(8, 0), 1, flights[i]);
        }
        for (int i = 1; i < 7; i++) {
            createReservation(user, flights[i]);
        }

    }
}