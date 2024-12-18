package com.example.backend.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.ReservationDTO;
import com.example.backend.DTOs.ReservationFilterCriteria;
import com.example.backend.Entities.Account;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.Role;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.AccountRepository;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;

@SpringBootTest
@Transactional
public class ReservationDisplayServiceTest {
    @Autowired
    private ReservationDisplayService reservationDisplayService;
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
    private User user;
    private Flight[] flights;

    @BeforeEach
    void setup(){
        generateData();
    }
    @Test
    void testFilterReservedCorrectence() {
        // given
        ReservationFilterCriteria criteria = new ReservationFilterCriteria(user.getUserId(), null, null, null, null,
                null, null, null, false, false);
        int expectedNumber = 6;
        // when
        PageResponse<ReservationDTO> page = reservationDisplayService.filterReserved(criteria, 0);
        // then
        Assertions.assertEquals(expectedNumber, page.content().size());
    }
    @Test
    void testFilterReserved2() {
        // given
        ReservationFilterCriteria criteria = new ReservationFilterCriteria(user.getUserId(), null, null, null, null,
                flights[4].getId(), null, null, false, false);
        int expectedNumber = 1;
        // when
        PageResponse<ReservationDTO> page = reservationDisplayService.filterReserved(criteria, 0);
        // then
        Assertions.assertEquals(expectedNumber, page.content().size());
    }
    @Test
    void testFilterReserved3() {
        // given
        ReservationFilterCriteria criteria = new ReservationFilterCriteria(user.getUserId(), "City1", "City2", null, null,
                null, null, null, true, false);
        int expectedNumber = 6;
        // when
        PageResponse<ReservationDTO> page = reservationDisplayService.filterReserved(criteria, 0);
        // then
        Assertions.assertEquals(expectedNumber, page.content().size());
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
                .flightId(flight.getId())
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
        Airport[] airports = new Airport[5];
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
        // create flightlegs
        // 1 leg for each flight
        for (int i = 1; i < 7; i++) {
            flights[i] = createFlights(departureDates[i - 1], arrivalDates[i - 1], 1500, 2000, 5, 13, false);
            FlightLeg flightLeg = createFlightLeg(airports[2], airports[1], LocalTime.of(12, 30), LocalTime.of(8, 0), 1,
                    flights[i]);
            flights[i].setFlightLegs(new ArrayList<>(List.of(flightLeg)));
            flightRepository.save(flights[i]);
        }
        for (int i = 1; i < 7; i++) {
            createReservation(user, flights[i]);
        }

    }
}
