package com.example.backend.Specifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Enums.FlightType;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.AirportRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@DataJpaTest
public class FlightSpecificationsTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightLegRepository flightLegRepository;
    @Autowired
    private AirportRepository airportRepository;

    private int firstFlightId;
    private Airport[] airports;
    private Flight[] flights;

    @BeforeEach
    void setup() {
        cleanDatabase();
        generateDate();
        firstFlightId = flights[1].getId();
    }

    @Test
    void testContainsDestinationCorrectence() {
        // given
        int expected_number_of_flights = 3;
        // where
        Specification<Flight> spec = FlightSpecifications.containsDestination("City5");
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 4, list.get(2).getId());
    }

    @Test
    void testContainsSourceCityCorrectence() {
        // given
        int expected_number_of_flights = 4;
        // where
        Specification<Flight> spec = FlightSpecifications.containsSource("City2");
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 1, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(2).getId());
        Assertions.assertEquals(firstFlightId + 4, list.get(3).getId());
    }

    @Test
    void testHasArrivalAirport() {
        // given
        int airportId = airports[5].getId();
        int expected_number_of_flights = 3;
        // where
        Specification<Flight> spec = FlightSpecifications.hasArrivalAirport(airportId);
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 4, list.get(2).getId());
    }

    @Test
    void testHasDepartureAirport() {
        // given
        int airportId = airports[2].getId();
        int expected_number_of_flights = 4;
        // where
        Specification<Flight> spec = FlightSpecifications.hasDepartureAirport(airportId);
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 1, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(2).getId());
        Assertions.assertEquals(firstFlightId + 4, list.get(3).getId());
    }

    @Test
    void testContainsStatusCancelled() {
        int expected_number_of_flights = 3;
        // where
        Specification<Flight> spec = FlightSpecifications.containsStatus("cancelled");
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
    }

    @Test
    void testContainsStatusCompleted() {
        // given
        int expected_number_of_flights = 2;
        // where
        Specification<Flight> spec = FlightSpecifications.containsStatus("complete");
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 4, list.get(1).getId());
    }

    @Test
    void testContainsStatusInCompleted() {
        // given
        int expected_number_of_flights = 3;
        // where
        Specification<Flight> spec = FlightSpecifications.containsStatus("incomplete");
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId + 1, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 2, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(2).getId());
    }

    @Test
    void testContainsStatusCancel() {
        int expected_number_of_flights = 3;
        // where
        Specification<Flight> spec = FlightSpecifications.containsStatusCancel();
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
    }

    @Test
    void testContainsStatusComplete() {
        // given
        int expected_number_of_flights = 2;
        // where
        Specification<Flight> spec = FlightSpecifications.containsStatusComplete();
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 4, list.get(1).getId());
    }

    @Test
    void testContainsStatusIncomplete() {
        // given
        int expected_number_of_flights = 3;
        // where
        Specification<Flight> spec = FlightSpecifications.containsStatusIncomplete();
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId + 1, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 2, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(2).getId());
    }

    @Test
    void testHasArrivalDateLessThan() {
        // given
        int expected_number_of_flights = 3;
        // where
        Specification<Flight> spec = FlightSpecifications.hasArrivalDateLessThan(LocalDate.of(2024, 10, 24));
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 2, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(2).getId());
    }

    @Test
    void testHasAvailableSeatsByEconomyClass() {
        // given
        int expected_number_of_flights = 1;
        // where
        Specification<Flight> spec = FlightSpecifications.hasAvailableSeats(SeatClass.ECONOMY, 5);
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId + 2, list.get(0).getId());
    }

    @Test
    void testHasAvailableSeatsByBusinessClass() {
        // given
        int expected_number_of_flights = 2;
        // where
        Specification<Flight> spec = FlightSpecifications.hasAvailableSeats(SeatClass.BUSINESS, 2);
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId + 2, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(1).getId());
    }

    @Test
    void testHasDepartureDate() {
        // given
        int expected_number_of_flights = 2;
        // where
        Specification<Flight> spec = FlightSpecifications.hasDepartureDate(LocalDate.of(2024, 10, 12));
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId + 1, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(1).getId());
    }

    @Test
    void testHasFlightTypeDirect() {
        // given
        int expected_number_of_flights = 1;
        // where
        Specification<Flight> spec = FlightSpecifications.hasFlightType(FlightType.DIRECT);
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId + 4, list.get(0).getId());
    }

    @Test
    void testHasFlightTypeIndirect() {
        // given
        int expected_number_of_flights = 4;
        // where
        Specification<Flight> spec = FlightSpecifications.hasFlightType(FlightType.INDIRECT);
        List<Flight> list = flightRepository.findAll(spec);
        // then
        Assertions.assertEquals(expected_number_of_flights, list.size());
        Assertions.assertEquals(firstFlightId, list.get(0).getId());
        Assertions.assertEquals(firstFlightId + 1, list.get(1).getId());
        Assertions.assertEquals(firstFlightId + 2, list.get(2).getId());
        Assertions.assertEquals(firstFlightId + 3, list.get(3).getId());
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

    private void generateDate(){
        // create 6 airports
        airports = new Airport[7];
        for (int i = 1; i < 7; i++)
            airports[i] = createAirport("Airport" + i, "City" + i, "Country" + i, "code" + i);

        flights = new Flight[6];
        int[] availableSeats = { 0, 0, 4, 10, 0, 0 };
        LocalDate[] departureDates = { LocalDate.of(2024, 10, 12), LocalDate.of(2024, 10, 20) };
        LocalDate[] arrivalDates = { LocalDate.of(2024, 10, 18), LocalDate.of(2024, 10, 24), LocalDate.of(2024, 10, 22),
                LocalDate.of(2024, 10, 23), LocalDate.of(2024, 10, 29) };
        boolean[] isCancel = { false, true, false, true, true };
        // create 5 flights
        for (int i = 1; i < 6; i++)
            flights[i] = createFlights(departureDates[i % 2], arrivalDates[i - 1], 1500, 2000,
                    availableSeats[i], availableSeats[6 - i], isCancel[i - 1]);


        // create flightlegs
        // 3 legs for flight 1 (source is City2 , destination is City5)
        for (int i = 2; i < 5; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i - 1, flights[1]);
        }
        // 2 legs for flight 2 (source is City2, destination is City4)
        for (int i = 2; i < 4; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i - 1, flights[2]);
        }
        // 2 legs for flight 3 (source is City1, destination is City3)
        for (int i = 1; i < 3; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i, flights[3]);
        }
        // 4 legs for flight 4 (source is City2, destination is City5)
        for (int i = 2; i < 5; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i - 1, flights[4]);
        }
        // 1 leg for flight 5 (source is City2, destination is City5)
        createFlightLeg(airports[5], airports[2], LocalTime.of(12, 30), LocalTime.of(8, 0), 1, flights[5]);

    }
}
