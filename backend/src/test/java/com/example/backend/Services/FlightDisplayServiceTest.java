// package com.example.backend.Services;

// import static org.mockito.Mockito.verifyNoInteractions;

// import java.time.LocalDate;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.test.annotation.Rollback;

// import com.example.backend.DTOMappers.AdminFlightMapper;
// import com.example.backend.Entities.Flight;
// import com.example.backend.Repositories.FlightRepository;
// import com.example.backend.Services.AdminDashboard.FlightDisplayService;

// @ExtendWith(MockitoExtension.class)
// public class FlightDisplayServiceTest {

//     @Mock
//     private FlightRepository flightRepository;
//     @Mock
//     private AdminFlightMapper adminFlightMapper;

//     @InjectMocks
//     private FlightDisplayService flightService;

//     private final LocalDate DEPARTURE_DATE = LocalDate.parse("2024-10-12");

//     @BeforeAll
//     @Rollback(value = false)
//     public void setup() {
//         LocalDate[] date = {
//                 LocalDate.parse("2024-10-12"), LocalDate.parse("2024-09-12"),
//                 LocalDate.parse("2024-08-12"), LocalDate.parse("2024-10-11"),
//                 LocalDate.parse("2024-10-12"), LocalDate.parse("2024-10-12"),
//                 LocalDate.parse("2024-10-12"), LocalDate.parse("2024-10-12"),
//                 LocalDate.parse("2024-08-12"), LocalDate.parse("2024-10-11"),
//                 LocalDate.parse("2024-10-12"), LocalDate.parse("2024-10-12"),
//                 LocalDate.parse("2024-10-12"), LocalDate.parse("2024-10-12"),
//                 LocalDate.parse("2024-08-12"), LocalDate.parse("2024-10-11"),
//                 LocalDate.parse("2024-10-12"), LocalDate.parse("2024-10-12") };

//         LocalDate arrivalDate = LocalDate.parse("2024-10-12");
//         int availableBusinessSeats = 10;
//         int availableEconomySeats = 50;
//         float economyPrice = 200;
//         float businessPrice = 2000;

//         for (int i = 0; i < date.length; i++) {
//             Flight flight = Flight.builder()
//                     .departureDate(date[i])
//                     .arrivalDate(arrivalDate)
//                     .economyPrice(economyPrice)
//                     .businessPrice(businessPrice)
//                     .availableEconomySeats(availableEconomySeats)
//                     .availableBusinessSeats(availableBusinessSeats)
//                     .build();

//         }
//     }

//     @Test
//     void testGetFlightsWhenDepartureDateIsNull() {
//         // given
//         LocalDate deparuterDate = null;
//         // when
//         Exception exception = Assertions.assertThrows(NullPointerException.class,
//                 () -> flightService.getFlights(deparuterDate, 0));
//         // then
//         Assertions.assertEquals("Departure date can't be null", exception.getMessage());
//         verifyNoInteractions(flightRepository);
//     }

//     @Test
//     void testGetFlightsWhenPageNumberIsNegative() {
//         // given
//         int pageNumber = -5;

//         // when
//         Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
//                 () -> flightService.getFlights(DEPARTURE_DATE, pageNumber));

//         // then
//         Assertions.assertEquals("Page number can't be negative", exception.getMessage());
//         verifyNoInteractions(flightRepository);
//     }

//     @Test
//     void testGetFlightsCorrectence() {
//         // given

//     }

//     @Test
//     void testFilterFlights() {
//         // given (input)
//         // when (test)
//         // then (compare with expected)
//     }

//     // test if pages or invaild
//     // test if any parameter is invaild
//     //
// }
