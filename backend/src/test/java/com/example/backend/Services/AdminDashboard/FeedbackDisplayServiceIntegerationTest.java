package com.example.backend.Services.AdminDashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.AdminDashboard.FeedbackFilterCriteria;
import com.example.backend.Entities.*;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.*;

@SpringBootTest
@Transactional
public class FeedbackDisplayServiceIntegerationTest {

    @Autowired
    private FeedbackDisplayService feedbackDisplayService;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FlightLegRepository flightLegRepository;
    @Autowired
    private AirportRepository airportRepository;

    @BeforeEach
    void setup() {
        generateData();
    }

    @Test
    void testFilterFeedbackByAllFeaturesCorrectence() {
        // given
        QualityRating cleanliness = QualityRating.EXCELLENT;
        QualityRating comfort = QualityRating.EXCELLENT;
        QualityRating service = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        QualityRating punctuality = QualityRating.EXCELLENT;
        short stars = 1;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service,
                punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(1, page.totalElements());
        Assertions.assertEquals((short) 1, page.content().get(0).stars());
        Assertions.assertEquals(QualityRating.EXCELLENT,
                page.content().get(0).comfort());
        Assertions.assertEquals(QualityRating.EXCELLENT,
                page.content().get(0).cleanliness());
        Assertions.assertEquals(QualityRating.EXCELLENT,
                page.content().get(0).punctuality());
        Assertions.assertEquals(QualityRating.EXCELLENT,
                page.content().get(0).foodAndBeverage());
        Assertions.assertEquals(QualityRating.EXCELLENT,
                page.content().get(0).service());
    }

    @Test
    void testFilterFeedbackWhenCleanlinesIsNull() {
        // given
        QualityRating cleanliness = null;
        QualityRating comfort = QualityRating.EXCELLENT;
        QualityRating service = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        QualityRating punctuality = QualityRating.EXCELLENT;
        short stars = 1;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service,
                punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(1, page.totalElements());

    }

    @Test
    void testFilterFeedbackWhenComfortIsNull() {
        // given
        QualityRating cleanliness = QualityRating.FAIR;
        QualityRating comfort = null;
        QualityRating service = QualityRating.FAIR;
        QualityRating foodAndBeverage = QualityRating.FAIR;
        QualityRating punctuality = QualityRating.FAIR;
        short stars = 1;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service,
                punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(2, page.totalElements());
    }

    @Test
    void testFilterFeedbackWhenServiceIsNull() {
        // given
        QualityRating cleanliness = QualityRating.EXCELLENT;
        QualityRating comfort = null;
        QualityRating service = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        QualityRating punctuality = QualityRating.EXCELLENT;
        short stars = 1;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service,
                punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(1, page.totalElements());
    }

    @Test
    void testFilterFeedbackWhenFoodAndBeverageIsNull() {
        // given
        QualityRating cleanliness = QualityRating.EXCELLENT;
        QualityRating comfort = null;
        QualityRating service = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        QualityRating punctuality = QualityRating.EXCELLENT;
        short stars = 1;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service,
                punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(1, page.totalElements());
    }

    @Test
    void testFilterFeedbackWhenPunctualityIsNull() {
        // given
        QualityRating cleanliness = QualityRating.EXCELLENT;
        QualityRating comfort = QualityRating.EXCELLENT;
        QualityRating service = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        QualityRating punctuality = null;
        short stars = 1;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service,
                punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(1, page.totalElements());
    }

    @Test
    void testFilterFeedbackWhenStarsIsNull() {
        // given
        QualityRating cleanliness = QualityRating.EXCELLENT;
        QualityRating comfort = QualityRating.EXCELLENT;
        QualityRating service = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        QualityRating punctuality = QualityRating.EXCELLENT;
        Short stars = null;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service,
                punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(1, page.totalElements());
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

    private Flight createFlight() {
        Flight flight = Flight.builder()
                .arrivalDate(LocalDate.of(2024, 12, 5))
                .departureDate(LocalDate.of(2024, 12, 5))
                .availableBusinessSeats(20)
                .availableEconomySeats(30)
                .businessPrice(200)
                .economyPrice(50)
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

    private Feedback createFeedback(User user, Flight flight, short stars, QualityRating service,
            QualityRating cleanliness, QualityRating comfort, QualityRating punctuality,
            QualityRating foodAndBeverage) {
        Feedback feedback = Feedback.builder()
                .user(user)
                .flight(flight)
                .cleanliness(cleanliness)
                .comfort(comfort)
                .service(service)
                .punctuality(punctuality)
                .foodAndBeverage(foodAndBeverage)
                .dateOfCreation(LocalDateTime.now())
                .stars(stars)
                .build();
        feedbackRepository.save(feedback);
        return feedback;
    }

    private void generateData() {
        User user = createUser(createAccount());

        Airport[] airports = new Airport[2];
        for (int i = 0; i < 2; i++)
            airports[i] = createAirport("Airport" + i, "City" + i, "Country" + i, "code" + i);

        Flight[] flights = new Flight[6];
        for (int i = 0; i < 6; i++) {
            flights[i] = createFlight();
            FlightLeg flightLeg = createFlightLeg(airports[1], airports[0], LocalTime.of(12, 30), LocalTime.of(8, 0), 1,
                    flights[i]);
            flights[i].setFlightLegs(new ArrayList<>(List.of(flightLeg)));
            flightRepository.save(flights[i]);
        }

        Feedback[] feedback = new Feedback[6];
        QualityRating[] cleanliness = {
                QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.POOR,
                QualityRating.FAIR, QualityRating.POOR, QualityRating.FAIR
        };
        QualityRating[] comfort = {
                QualityRating.EXCELLENT, QualityRating.POOR, QualityRating.EXCELLENT,
                QualityRating.POOR, QualityRating.EXCELLENT, QualityRating.FAIR
        };
        QualityRating[] service = {
                QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.EXCELLENT,
                QualityRating.EXCELLENT, QualityRating.EXCELLENT, QualityRating.FAIR
        };
        QualityRating[] punctuality = {
                QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.EXCELLENT,
                QualityRating.EXCELLENT, QualityRating.EXCELLENT, QualityRating.FAIR
        };
        QualityRating[] foodAndBeverage = {
                QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.POOR,
                QualityRating.EXCELLENT, QualityRating.EXCELLENT, QualityRating.FAIR
        };
        short[] stars = { 1, 1, 2, 5, 3,1 };
        for (int i = 0; i < 6; i++)
            feedback[i] = createFeedback(user, flights[i], stars[i], service[i], cleanliness[i], comfort[i],
                    punctuality[i], foodAndBeverage[i]);
    }
}