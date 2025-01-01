package com.example.backend.Services;

import java.util.Optional;

import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.Entities.*;
import com.example.backend.Repositories.FeedbackRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServices {

    private final FeedbackRepository feedbackRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public FeedbackServices(FeedbackRepository feedbackRepository, FlightRepository flightRepository, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.flightRepository = flightRepository;
        this.userRepository  = userRepository;
    }
    public boolean addFeedback(FeedbackDTO feedbackDTO) {

        System.out.println("arrived");

        Optional<Flight> optionalFlight = this.flightRepository.findFlightByFlightId(feedbackDTO.flightId());
        if(optionalFlight.isEmpty()){
            throw new IllegalArgumentException("There is no flight with that id");
        }

        Optional<User> optionalUser = this.userRepository.findUserByUserId(feedbackDTO.userId());
        if(optionalUser.isEmpty()){
            throw new IllegalArgumentException("There is no user with that id");
        }

        System.out.println("arrived");

        Feedback feedback = new Feedback();
        feedback.setCleanliness(feedbackDTO.cleanliness());
        feedback.setService(feedbackDTO.service());
        feedback.setComfort(feedbackDTO.comfort());
        feedback.setFlight(optionalFlight.get());
        feedback.setComment(feedbackDTO.comment());
        feedback.setStars(feedbackDTO.stars());
        feedback.setUser(optionalUser.get());
        feedback.setDateOfCreation(feedbackDTO.dateOfCreation());
        feedback.setFoodAndBeverage(feedbackDTO.foodAndBeverage());
        feedback.setFlightId(feedbackDTO.flightId());
        feedback.setPunctuality(feedbackDTO.punctuality());
        feedback.setUserId(feedbackDTO.userId());

        this.feedbackRepository.save(feedback);
        return true;

    }
}
