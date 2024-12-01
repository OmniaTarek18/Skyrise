package com.example.backend.Repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import com.example.backend.Entities.Flight;
import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.User;
import com.example.backend.Enums.SeatClass;

@DataJpaTest
public class ReservationRepoTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    @DisplayName("Test 1: Find reserved flights by user id returns correct reserved flights")
    @Rollback(true)
    void testFindByUserIdCorrectness () {

        List<Reservation> actual = new ArrayList<>();
        for (int i=1; i<4; i++) {
            User user = new User();
            userRepository.save(user);

            Flight flight = Flight.builder()
                    .departureDate(LocalDate.of(2024, 12, 1))
                    .arrivalDate(LocalDate.of(2024, 12, 1))
                    .economyPrice(100.0f)
                    .businessPrice(200.0f)
                    .availableEconomySeats(100)
                    .availableBusinessSeats(50)
                    .isCancel(false)
                    .build();
            flightRepository.save(flight);

            Reservation reservation = new Reservation();
            reservation.setUserId(i);
            reservation.setFlightId(i);
            reservation.setUser(user);
            reservation.setFlight(flight);
            reservation.setSeatClass(SeatClass.ECONOMY);
            reservation.setReservedSeats(2);

            reservationRepository.save(reservation);
            if (i == 1) {
                actual.add(reservation);
            }
        }

        for (int i=2; i<4; i++) {
            Reservation reservation = new Reservation();
            reservation.setUserId(1);
            reservation.setFlightId(i);
            reservation.setUser(userRepository.findById(1).orElseThrow(() -> new IllegalStateException("User not found")));
            reservation.setFlight(flightRepository.findById(i).orElseThrow(() -> new IllegalStateException("Flight not found")));
            reservation.setSeatClass(SeatClass.ECONOMY);
            reservation.setReservedSeats(2);

            reservationRepository.save(reservation);
            actual.add(reservation);
        }

        Page<Reservation> reservations = reservationRepository.findByUserId(1, PageRequest.of(0, 10));

        assertNotNull(reservations);
        assertEquals(3, reservations.getTotalElements());
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().containsExactlyElementsOf(reservations);
    }

    @Test
    @DisplayName("Test 2: Find reserved flights by user id returns empty list")
    @Rollback(true)
    void testFindByUserIdReturnsEmpty() {

        Page<Reservation> reservations = reservationRepository.findByUserId(1, PageRequest.of(0, 10));

        assertNotNull(reservations);
        assertTrue(reservations.isEmpty());
    }
}
