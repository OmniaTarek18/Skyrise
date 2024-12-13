package com.example.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.DTOMappers.PassengerMapper;
import com.example.backend.DTOs.PassengerDTO;
import com.example.backend.Entities.Passenger;
import com.example.backend.Entities.Reservation;
import com.example.backend.Repositories.PassengerRepository;
import com.example.backend.Repositories.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final ReservationRepository reservationRepository;

    public void addPassengers(List<PassengerDTO> passengersDTOs, Integer userId, Integer flightId) {
        Optional<Reservation> optionalReservation = reservationRepository.findByFlightIdAndUserId(flightId, userId);

        if (optionalReservation.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found.");
        }
        Reservation reservation = optionalReservation.get();

        if (passengersDTOs.size() != reservation.getReservedSeats()) {
            throw new IllegalArgumentException("Number of passengers doesn't match the reserved seats.");
        }

        List<Passenger> passengers = passengersDTOs.stream().map(dto -> PassengerMapper.toEntity(dto, reservation)).toList();

        passengerRepository.saveAll(passengers);
    }
}
