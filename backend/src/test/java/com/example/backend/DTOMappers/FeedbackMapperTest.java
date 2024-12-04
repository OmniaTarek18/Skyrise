package com.example.backend.DTOMappers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.User;
import com.example.backend.Enums.QualityRating;

public class FeedbackMapperTest {

    private User user;
    private Flight flight;
    private QualityRating cleanliness = QualityRating.EXCELLENT;
    private QualityRating comfort = QualityRating.POOR;
    private QualityRating service = QualityRating.FAIR;
    private QualityRating punctuality = QualityRating.EXCELLENT;
    private QualityRating foodAndBeverage = QualityRating.EXCELLENT;
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @Test
    void testToDTO() {
        // given
        setupEntities();
        short stars = 3;
        int numberOfLegs = flight.getFlightLegs().size();
        Feedback feedback = Feedback.builder()
                .user(user)
                .flight(flight)
                .cleanliness(cleanliness)
                .comfort(comfort)
                .service(service)
                .punctuality(punctuality)
                .foodAndBeverage(foodAndBeverage)
                .dateOfCreation(dateOfCreation)
                .stars(stars)
                .build();
        // where
        FeedbackDTO actualDto = FeedbackMapper.toDTO(feedback);
        // then
        Assertions.assertEquals(user.getId(), actualDto.userId());
        Assertions.assertEquals(flight.getId(), actualDto.flightId());
        Assertions.assertEquals(stars, actualDto.stars());
        Assertions.assertEquals(null, actualDto.comment());
        Assertions.assertEquals(comfort, actualDto.comfort());
        Assertions.assertEquals(service, actualDto.service());
        Assertions.assertEquals(cleanliness, actualDto.cleanliness());
        Assertions.assertEquals(punctuality, actualDto.punctuality());
        Assertions.assertEquals(foodAndBeverage, actualDto.foodAndBeverage());
        Assertions.assertEquals(dateOfCreation, actualDto.dateOfCreation());
        Assertions.assertEquals(user.getFirstName(), actualDto.userName());
        Assertions.assertEquals(flight.getDepartureDate(), actualDto.flightDepartureDate());
        Assertions.assertEquals(flight.getFlightLegs().get(0).getDepartureAirport().getAirportCity(),
                actualDto.flightSource());
        Assertions.assertEquals(flight.getFlightLegs().get(numberOfLegs - 1).getArrivalAirport().getAirportCity(),
                actualDto.flightDestination());

    }

    @Test
    void testToEntity() {
        // given
        Integer userId = 1;
        Integer flightId = 2;
        short stars = 3;
        String userName = "Ahmed";
        String departureCity = "Cairo";
        String arrivalCity = "London";
        String comment = "It was a good flight";
        LocalDate departureDate = LocalDate.of(2024, 12, 5);
        
        FeedbackDTO feedbackDTO = new FeedbackDTO(
            userId,
            flightId,
            stars,
            comment,
            userName,
            departureCity,
            arrivalCity,
            departureDate,
            comfort,
            service,
            punctuality,
            foodAndBeverage,
            cleanliness,
            dateOfCreation);
        // where
        Feedback actualFeedback = FeedbackMapper.toEntity(feedbackDTO);
        // then 
        Assertions.assertEquals(stars, actualFeedback.getStars());
        Assertions.assertEquals(userId, actualFeedback.getUserId());
        Assertions.assertEquals(flightId, actualFeedback.getFlightId());
        Assertions.assertEquals(comfort, actualFeedback.getComfort());
        Assertions.assertEquals(comment, actualFeedback.getComment());
        Assertions.assertEquals(service, actualFeedback.getService());
        Assertions.assertEquals(punctuality, actualFeedback.getPunctuality());
        Assertions.assertEquals(cleanliness, actualFeedback.getCleanliness());
        Assertions.assertEquals(dateOfCreation, actualFeedback.getDateOfCreation());
        Assertions.assertEquals(foodAndBeverage, actualFeedback.getFoodAndBeverage());
    }

    @Test 
    void testToEntityWhenDTOIsNull(){
        assertThrows(NullPointerException.class, () -> FeedbackMapper.toEntity(null));
    }

    @Test 
    void testToDTOWhenEntityIsNull(){
        assertThrows(NullPointerException.class, () -> FeedbackMapper.toDTO(null));
    }

    void setupEntities() {
        String lastName = "Osama";
        String firstName = "Ahmed";
        user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        flight = Flight.builder()
                .arrivalDate(LocalDate.of(2024, 12, 5))
                .departureDate(LocalDate.of(2024, 12, 5))
                .availableBusinessSeats(20)
                .availableEconomySeats(30)
                .businessPrice(200)
                .economyPrice(50)
                .build();

        Airport departureAirport = Airport.builder()
                .airportName("JFK International Airport")
                .airportCountry("USA")
                .airportCity("New York")
                .airportCode("JFK")
                .build();

        Airport arrivalAirport = Airport.builder()
                .airportName("LAX International Airport")
                .airportCountry("USA")
                .airportCity("Los Angeles")
                .airportCode("LAX")
                .build();

        FlightLeg flightLeg = FlightLeg.builder()
                .flightLegId(1)
                .flight(flight)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureTime(LocalTime.of(10, 30))
                .arrivalTime(LocalTime.of(12, 30))
                .build();
        List<FlightLeg> flightLegs = new ArrayList<>();
        flightLegs.add(flightLeg);
        flight.setFlightLegs(flightLegs);

    }

}
