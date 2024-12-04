package com.example.backend.DTOMappers;

import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.FlightLeg;

public class FeedbackMapper {

    public static FeedbackDTO toDTO(Feedback entity) {
        if (entity == null)
            throw new NullPointerException("Feedback entity is null!!");
        int numberOfLegs = entity.getFlight().getFlightLegs().size();
        FlightLeg first = entity.getFlight().getFlightLegs().get(0);
        FlightLeg last = entity.getFlight().getFlightLegs().get(numberOfLegs - 1);

        return new FeedbackDTO(
                entity.getUserId(),
                entity.getFlightId(),
                entity.getStars(),
                entity.getComment(),
                entity.getUser().getFirstName(),
                first.getDepartureAirport().getAirportCity(),
                last.getArrivalAirport().getAirportCity(),
                entity.getFlight().getDepartureDate(),
                entity.getComfort(),
                entity.getService(),
                entity.getPunctuality(),
                entity.getFoodAndBeverage(),
                entity.getCleanliness(),
                entity.getDateOfCreation());
    }

    public static Feedback toEntity(FeedbackDTO feedbackDTO) {
        if (feedbackDTO == null)
            throw new NullPointerException("FeedbackDTO is null!!");
        Feedback entity = Feedback.builder()
                .stars(feedbackDTO.stars())
                .userId(feedbackDTO.userId())
                .flightId(feedbackDTO.flightId())
                .comfort(feedbackDTO.comfort())
                .comment(feedbackDTO.comment())
                .service(feedbackDTO.service())
                .punctuality(feedbackDTO.punctuality())
                .cleanliness(feedbackDTO.cleanliness())
                .foodAndBeverage(feedbackDTO.foodAndBeverage())
                .dateOfCreation(feedbackDTO.dateOfCreation())
                .build();
        return entity;
    }

}
