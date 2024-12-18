package com.example.backend.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Reservation;
import com.example.backend.TestData.TestDataInitializer;

@DataJpaTest
@Transactional
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private FlightLegRepository flightLegRepository;

    @BeforeEach
    public void setup() {
        TestDataInitializer data = new TestDataInitializer(reservationRepository, accountRepository,
                userRepository, flightRepository, airportRepository, flightLegRepository);
        data.generateData();
        airportRepository = data.getAirportRepository();
        flightRepository = data.getFlightRepository();
        flightLegRepository = data.getFlightLegRepository();
        accountRepository = data.getAccountRepository();
        userRepository = data.getUserRepository();
        reservationRepository = data.getReservationRepository();
    }

    @Test
    @DisplayName("Test 1: Find reserved flight by user id and flight id returns correct reserved flight")
    @Rollback(true)
    void testFindByFlightIdAndUserIdCorrectness() {
        Optional<Reservation> reservation =
        reservationRepository.findByFlightIdAndUserId(1, 1);

        assertNotNull(reservation);
        assertEquals(1, reservation.get().getUser().getUserId());
        assertEquals(1, reservation.get().getFlight().getId());
    }

}