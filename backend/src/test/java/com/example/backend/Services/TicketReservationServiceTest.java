package com.example.backend.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.DTOs.TicketDTO;
import com.example.backend.Entities.Account;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.Role;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.AccountRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class TicketReservationServiceTest {

    @Autowired
    private TicketReservationService ticketReservationService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private User user;
    private Flight flight;

    @BeforeEach
    void setup() {
        user = createUser(createAccount());
        flight = createFlights(LocalDate.of(2024, 12, 20), LocalDate.of(2024, 12, 20), 1500, 2000, 50, 20, false);
        userRepository.save(user);
        flightRepository.save(flight);

    }

    @Test
    void testCreateReservationSuccess() {
        // given
        TicketDTO ticketDTO = new TicketDTO(user.getUserId(), flight.getId(), SeatClass.ECONOMY, 3);

        // when
        ticketReservationService.createReservation(ticketDTO);

        // then
        Assertions.assertEquals(47, flightRepository.findById(flight.getId()).get().getAvailableEconomySeats());
        Assertions.assertTrue(reservationRepository.existsByUserIdAndFlightId(user.getUserId(), flight.getId()));
    }

    @Test
    void testCreateReservationSeatUnavailable() {
        // given
        TicketDTO ticketDTO = new TicketDTO(user.getUserId(), flight.getId(), SeatClass.ECONOMY, 60);

        // when / then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ticketReservationService.createReservation(ticketDTO));
    }

    @Test
    void testCancelReservationSuccess() {
        // given
        TicketDTO ticketDTO = new TicketDTO(user.getUserId(), flight.getId(), SeatClass.BUSINESS, 2);
        ticketReservationService.createReservation(ticketDTO);

        // when
        ticketReservationService.cancelReservation(flight.getId(), user.getUserId());

        // then
        Assertions.assertEquals(20, flightRepository.findById(flight.getId()).get().getAvailableBusinessSeats());
        Assertions.assertFalse(reservationRepository.existsByUserIdAndFlightId(user.getUserId(), flight.getId()));
    }

    @Test
    void testCancelReservationNotExist() {
        // when / then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ticketReservationService.cancelReservation(flight.getId(), user.getUserId()));
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

}
