package com.example.backend.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

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

@ExtendWith(MockitoExtension.class)
public class ReservationDisplayServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private FlightLegRepository flightLegRepository;

    @InjectMocks
    private ReservationDisplayService reservationDisplayService;

    @BeforeEach
    public void setup() {
        
    }

    @Test
    @DisplayName("Test 1: get reserved flights by user id returns correct reserved flights")
    @Rollback(true)
    void testGetReservationByUserIdCorrectness() {

        Airport departureAirport = Airport.builder()
                .airportName("JFK International Airport")
                .airportCountry("USA")
                .airportCity("New York")
                .airportCode("JFK")
                .latitude(40.6413)
                .longitude(-73.7781)
                .build();
        airportRepository.save(departureAirport);

        Airport arrivalAirport = Airport.builder()
                .airportName("LAX International Airport")
                .airportCountry("USA")
                .airportCity("Los Angeles")
                .airportCode("LAX")
                .latitude(33.9416)
                .longitude(-118.4085)
                .build();
        airportRepository.save(arrivalAirport);

        List<Reservation> actual = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
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

            FlightLeg flightLeg = FlightLeg.builder()
                    .flightLegId(1)
                    .flightId(i)
                    .flight(flight)
                    .departureAirport(departureAirport)
                    .arrivalAirport(arrivalAirport)
                    .departureTime(LocalTime.of(10, 30))
                    .arrivalTime(LocalTime.of(12, 30))
                    .build();
            flightLegRepository.save(flightLeg);

            flight.setFlightLegs(List.of(flightLeg));
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

        Page<Reservation> actualPage = new PageImpl<>(actual);

        given(reservationRepository.findByUserId(1, PageRequest.of(0, 10))).willReturn(actualPage);

        var result = reservationDisplayService.getReservationByUserId(1, 0);

        assertNotNull(result);
        assertEquals(1, result.content().size());
    }

    // @Test
    // @DisplayName("Test 2: get reserved flights by user id and filter based on some criteria")
    // @Rollback(true)
    // void testFilterReservedCorrectness() {

    // }
}
