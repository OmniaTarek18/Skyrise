package com.example.backend.Repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import com.example.backend.HelperData;
import com.example.backend.Entities.Reservation;

@DataJpaTest
public class ReservationRepoTest {

    @Autowired
    private ReservationRepository reservationRepository;

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
        HelperData data = new HelperData(airportRepository, flightRepository, flightLegRepository, userRepository,
                reservationRepository);
        data.dataGeneration();
        airportRepository = data.getAirportRepository();
        flightRepository = data.getFlightRepository();
        flightLegRepository = data.getFlightLegRepository();
        userRepository = data.getUserRepository();
        reservationRepository = data.getReservationRepository();
    }

    @Test
    @DisplayName("Test 1: Find reserved flights by user id returns correct reserved flights")
    @Rollback(true)
    void testFindByUserIdCorrectness () {

        Page<Reservation> reservations = reservationRepository.findByUserId(1, PageRequest.of(0, 10));

        List<Integer> userIds = reservations.stream()
                .map(reservation -> reservation.getUserId())
                .collect(Collectors.toList());

        List<Integer> actual = List.of(1);

        assertNotNull(reservations);
        assertEquals(1, reservations.getTotalElements());
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().containsExactlyElementsOf(userIds);
    }

    @Test
    @DisplayName("Test 2: Find reserved flights by user id returns empty list")
    @Rollback(true)
    void testFindByUserIdReturnsEmpty() {

        Page<Reservation> reservations = reservationRepository.findByUserId(5, PageRequest.of(0, 10));

        assertNotNull(reservations);
        assertTrue(reservations.isEmpty());
    }
}
