package com.example.backend.Services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.backend.DTOs.InsightsDTO;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;

@Service
public class InsightsService {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final ReservationRepository reservationRepository;

    public InsightsService(UserRepository userRepository, FlightRepository flightRepository,
            ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.reservationRepository = reservationRepository;
    }

    public InsightsDTO getInsights() {
        LocalDate today = LocalDate.now();
        return new InsightsDTO(
                userRepository.count(), 
                flightRepository.countByArrivalDateGreaterThanEqual(today),
                reservationRepository.count());
    }
}
