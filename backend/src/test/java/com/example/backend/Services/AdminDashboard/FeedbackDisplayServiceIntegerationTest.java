package com.example.backend.Services.AdminDashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.AdminDashboard.FeedbackFilterCriteria;
import com.example.backend.Entities.Account;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FeedbackRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.UserRepository;

@SpringBootTest
public class FeedbackDisplayServiceIntegerationTest {

    @Autowired
    private FeedbackDisplayService feedbackDisplayService;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightLegRepository flightLegRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        cleanDatabase();
        generateData();
    }

    @Test
    void testFilterFeedbackByAllFeatures(){
        // given
        QualityRating cleanliness = QualityRating.EXCELLENT;
        QualityRating comfort = QualityRating.EXCELLENT;
        QualityRating service  = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        QualityRating punctuality = QualityRating.EXCELLENT;
        short stars = 1;
        // when
        FeedbackFilterCriteria feedbackFilterCriteria = new FeedbackFilterCriteria(stars, null, comfort, service, punctuality, foodAndBeverage, cleanliness);
        PageResponse<FeedbackDTO> page =feedbackDisplayService.filterFeedback(feedbackFilterCriteria, 0);
        // then
        Assertions.assertEquals(1, page.totalElements());
        Assertions.assertEquals((short) 1, page.content().get(0).stars());
        Assertions.assertEquals(QualityRating.EXCELLENT, page.content().get(0).comfort());
        Assertions.assertEquals(QualityRating.EXCELLENT, page.content().get(0).cleanliness());
        Assertions.assertEquals(QualityRating.EXCELLENT, page.content().get(0).punctuality());
        Assertions.assertEquals(QualityRating.EXCELLENT, page.content().get(0).foodAndBeverage());
        Assertions.assertEquals(QualityRating.EXCELLENT, page.content().get(0).service());
    }

    private void cleanDatabase() {
        userRepository.deleteAll();
        feedbackRepository.deleteAll();
        flightRepository.deleteAll();
        flightLegRepository.deleteAll();
        airportRepository.deleteAll();
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
                .flightId(flight.getId())
                .build();
        flightLegRepository.save(flightLeg);
        return flightLeg;
    }

    private User creatUser() {
        Account account = Account.builder()
                .accountId(1)
                .email("example@gmail.com")
                .password("password")
                .role(Role.USER)
                .build();

        User user = User.builder()
                .userId(1)
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

    private void createFeedback(User user, Flight flight, short stars, QualityRating cleanliness, QualityRating comfort,
            QualityRating service, QualityRating punctuality, QualityRating foodAndBeverage, LocalDateTime time) {
        Feedback feedback = Feedback.builder()
                .user(user)
                .flight(flight)
                .cleanliness(QualityRating.FAIR)
                .comfort(QualityRating.EXCELLENT)
                .service(QualityRating.EXCELLENT)
                .punctuality(QualityRating.EXCELLENT)
                .foodAndBeverage(QualityRating.EXCELLENT)
                .dateOfCreation(LocalDateTime.now())
                .stars(stars)
                .build();
        feedbackRepository.save(feedback);
    }

    private void generateData() {
        // create 2 airports
        Airport[] airports = new Airport[7];
        for (int i = 1; i < 7; i++)
            airports[i] = createAirport("Airport" + i, "City" + i, "Country" + i, "code" + i);

        // create 5 flights
        Flight[] flights = new Flight[6];
        LocalDate departureDate = LocalDate.of(2024, 10, 20);
        LocalDate arrivalDates = LocalDate.of(2024, 10, 18);

        for (int i = 1; i < 6; i++)
            flights[i] = createFlights(departureDate, arrivalDates, 1500, 2000,
                    10, 20, false);

        // create flightlegs
        // one leg for each flight
        for (int i = 1; i < 6; i++) {
            createFlightLeg(airports[2], airports[1], LocalTime.of(12, 30), LocalTime.of(8, 0), 1, flights[i]);
        }   // create flightlegs
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

        // create one user
        User user = creatUser();

        // create feedback
        QualityRating[] cleanliness = { QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.POOR,
                QualityRating.FAIR, QualityRating.EXCELLENT, QualityRating.FAIR };

        QualityRating[] comfort = { QualityRating.EXCELLENT, QualityRating.POOR, QualityRating.EXCELLENT,
                QualityRating.POOR, QualityRating.EXCELLENT, QualityRating.FAIR };

        QualityRating[] service = { QualityRating.EXCELLENT, QualityRating.POOR, QualityRating.FAIR,
                QualityRating.EXCELLENT, QualityRating.POOR, QualityRating.POOR };

        QualityRating[] punctuality = { QualityRating.EXCELLENT, QualityRating.POOR, QualityRating.POOR,
                QualityRating.FAIR, QualityRating.FAIR, QualityRating.POOR };

        QualityRating[] foodAndBeverage = { QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.POOR,
                QualityRating.FAIR, QualityRating.EXCELLENT, QualityRating.FAIR };

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime[] time = { now.plusDays(2), now.plusDays(1), now.plusDays(4), now.plusDays(5), now.plusDays(6) };
        short[] stars = { 1, 2, 2, 1, 3, 4 };
        for (int i = 1; i < 6; i++) {
            createFeedback(user, flights[i], stars[i], cleanliness[i], comfort[i], service[i], punctuality[i],
                    foodAndBeverage[i], time[i]);
        }
    }

}