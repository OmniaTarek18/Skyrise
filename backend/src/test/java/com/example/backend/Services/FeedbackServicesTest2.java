package com.example.backend.Services;

import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.Entities.Account;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@Transactional
public class FeedbackServicesTest2 {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackServices feedbackServices;

    @Autowired
    private AccountRepository accountRepository;



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
        LocalDate date = LocalDate.of(2024, 10, 8);

        Flight flight = Flight.builder()
                .isCancel(false)
                .departureDate(date)
                .arrivalDate(date)
                .economyPrice(1000)
                .businessPrice(2000)
                .availableEconomySeats(13)
                .availableBusinessSeats(3)
                .build();
        flightRepository.save(flight);
        return flight;
    }


    private FeedbackDTO createFeedbackDTO(int status){
        User user = createUser(createAccount());
        Flight flight = createFlight();

        return FeedbackDTO.builder().
                service(QualityRating.EXCELLENT).comfort(QualityRating.EXCELLENT).
                dateOfCreation(LocalDateTime.of(2000, 3, 21, 2, 44)).
                cleanliness(QualityRating.FAIR).flightDepartureDate(LocalDate.of(2000, 10, 10)).
                flightDestination("Egypt").flightId(1).flightSource("India").stars((short) 4).
                foodAndBeverage(QualityRating.POOR).comment("Not Staedy Enough").
                punctuality(QualityRating.POOR).userId(status == 1 ? 1000 : user.getUserId()).userName("Adel").
                flightId(status == 2 ? 1000 : flight.getId()).
                build();
    }

    @Test
    public void addFeedbackTest(){

        FeedbackDTO feedbackDTO = createFeedbackDTO(0);
        assertEquals(true, feedbackServices.addFeedback(feedbackDTO));

    }

    @Test
    public void addFeedbackTest2(){
        FeedbackDTO feedbackDTO = createFeedbackDTO(1);

        try{
            feedbackServices.addFeedback(feedbackDTO);
            fail("Exception should have been thrown");
        }
        catch (Exception e){
            assertEquals("There is no user with that id", e.getMessage());
        }

    }@Test
    public void addFeedbackTest3(){
        FeedbackDTO feedbackDTO = createFeedbackDTO(2);

        try{
            feedbackServices.addFeedback(feedbackDTO);
            fail("Exception should have been thrown");
        }
        catch (Exception e){
            assertEquals("There is no flight with that id", e.getMessage());
        }

    }

}
