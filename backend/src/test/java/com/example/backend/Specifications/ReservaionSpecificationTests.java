package com.example.backend.Specifications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Reservation;
import com.example.backend.Repositories.AccountRepository;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.TestData.TestDataInitializer;


@SpringBootTest
@Transactional
public class ReservaionSpecificationTests {

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
        TestDataInitializer data = new TestDataInitializer(reservationRepository, accountRepository, userRepository,
                flightRepository, airportRepository, flightLegRepository);
        // data.cleanDatabase();
        data.generateData();

        accountRepository = data.getAccountRepository();
        airportRepository = data.getAirportRepository();
        flightRepository = data.getFlightRepository();
        flightLegRepository = data.getFlightLegRepository();
        userRepository = data.getUserRepository();
        reservationRepository = data.getReservationRepository();
    }

    @Test
    @DisplayName("Test 1: Specification filters reservations by userId")
    @Rollback(true)
    void testContainsUserIdSpecification() {

        Specification<Reservation> spec = ReservationSpecifications.containsId("userId", 1);
        List<Reservation> results = reservationRepository.findAll(spec);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(1, results.get(0).getUser().getUserId());
    }

    @Test
    @DisplayName("Test 2: Specification filters reservations by fligthId")
    @Rollback(true)
    void testContainsFlightIdSpecification() {

        Specification<Reservation> spec = ReservationSpecifications.containsId("flightId", 1);
        List<Reservation> results = reservationRepository.findAll(spec);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(1, results.get(0).getFlight().getId());
    }

    @Test
    @DisplayName("Test 3: Specification filters reservations by source")
    @Rollback(true)
    void testContainsSourceSpecification() {

        Specification<Reservation> spec = ReservationSpecifications.containsLocation("departureAirport",
                "New York");
        List<Reservation> results = reservationRepository.findAll(spec);
        Airport resultDepartureAirport = results.get(0).getFlight().getFlightLegs().get(0).getDepartureAirport();
        String resultSourceCity = resultDepartureAirport.getAirportCity();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("New York", resultSourceCity);
    }

    @Test
    @DisplayName("Test 4: Specification filters reservations by destination")
    @Rollback(true)
    void testContainsDestinationSpecification() {

        Specification<Reservation> spec = ReservationSpecifications.containsLocation("arrivalAirport",
                "Los Angeles");
        List<Reservation> results = reservationRepository.findAll(spec);
        Airport resultDepartureAirport = results.get(0).getFlight().getFlightLegs().get(0).getArrivalAirport();
        String resultSourceCity = resultDepartureAirport.getAirportCity();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Los Angeles", resultSourceCity);
    }

    @Test
    @DisplayName("Test 5: Specification filters reservations by departure date")
    @Rollback(true)
    void testContainsDepartureDateSpecification() {

        Specification<Reservation> spec = ReservationSpecifications
                .containsDate("departureDate", LocalDate.parse("2024-12-20"));
        List<Reservation> results = reservationRepository.findAll(spec);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(LocalDate.parse("2024-12-20"), results.get(0).getFlight().getDepartureDate());
    }

    @Test
    @DisplayName("Test 6: Specification filters reservations by arrival date")
    @Rollback(true)
    void testContainsArrivalDateSpecification() {

        Specification<Reservation> spec = ReservationSpecifications
                .containsDate("arrivalDate", LocalDate.parse("2024-12-21"));
        List<Reservation> results = reservationRepository.findAll(spec);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(LocalDate.parse("2024-12-21"), results.get(0).getFlight().getArrivalDate());
    }

//     @Test
//     @DisplayName("Test 7: Specification sorts by departure date ascendingly")
//     @Rollback(true)
//     void testSortedByDepartureDateAscendingly() {

//         Specification<Reservation> specAsc = ReservationSpecifications.sortedByDate("departureDate", "ASC");

//         List<Reservation> sortedReservationsAsc = reservationRepository.findAll(specAsc);

//         List<LocalDate> departureDatesAsc = sortedReservationsAsc.stream()
//                 .map(reservation -> reservation.getFlight().getDepartureDate())
//                 .collect(Collectors.toList());

//         List<LocalDate> expectedAsc = List.of(
//                 LocalDate.parse("2024-08-12"),
//                 LocalDate.parse("2024-09-12"),
//                 LocalDate.parse("2024-10-12"));

//         assertEquals(expectedAsc, departureDatesAsc);
//     }

//     @Test
//     @DisplayName("Test 8: Specification sorts by departure date descendingly")
//     @Rollback(true)
//     void testSortedByDepartureDateDescendingly() {

//         Specification<Reservation> specDesc = ReservationSpecifications.sortedByDate("departureDate", "Desc");

//         List<Reservation> sortedReservationsDesc = reservationRepository.findAll(specDesc);

//         List<LocalDate> departureDatesDesc = sortedReservationsDesc.stream()
//                 .map(reservation -> reservation.getFlight().getDepartureDate())
//                 .collect(Collectors.toList());

//         List<LocalDate> expectedDesc = List.of(
//                 LocalDate.parse("2024-10-12"),
//                 LocalDate.parse("2024-09-12"),
//                 LocalDate.parse("2024-08-12"));
//         assertEquals(expectedDesc, departureDatesDesc);
//     }

//     @Test
//     @DisplayName("Test 9: Specification sorts by arrival date ascendingly")
//     @Rollback(true)
//     void testSortedByArrivalDateAscendingly() {

//         Specification<Reservation> specAsc = ReservationSpecifications.sortedByDate("arrivalDate", "ASC");

//         List<Reservation> sortedReservationsAsc = reservationRepository.findAll(specAsc);

//         List<LocalDate> arrivalDatesAsc = sortedReservationsAsc.stream()
//                 .map(reservation -> reservation.getFlight().getArrivalDate())
//                 .collect(Collectors.toList());

//         List<LocalDate> expectedAsc = List.of(
//                 LocalDate.parse("2024-08-12"),
//                 LocalDate.parse("2024-09-12"),
//                 LocalDate.parse("2024-10-11"));

//         assertEquals(expectedAsc, arrivalDatesAsc);
//     }

//     @Test
//     @DisplayName("Test 10: Specification sorts by arrival date descendingly")
//     @Rollback(true)
//     void testSortedByArrivalDateDescendingly() {

//         Specification<Reservation> specDesc = ReservationSpecifications.sortedByDate("arrivalDate", "Desc");

//         List<Reservation> sortedReservationsDesc = reservationRepository.findAll(specDesc);

//         List<LocalDate> departureDatesDesc = sortedReservationsDesc.stream()
//                 .map(reservation -> reservation.getFlight().getArrivalDate())
//                 .collect(Collectors.toList());

//         List<LocalDate> expectedDesc = List.of(
//                 LocalDate.parse("2024-10-11"),
//                 LocalDate.parse("2024-09-12"),
//                 LocalDate.parse("2024-08-12"));

//         assertEquals(expectedDesc, departureDatesDesc);
//     }

}