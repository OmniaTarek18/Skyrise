package com.example.backend.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.DTOs.TicketDTO;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.User;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Utilites.ValidateInput;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketReservationService {
    
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    @Transactional
    public void reserveTicket(TicketDTO ticketDTO) {
        // Checking for nulls or illegal input
        ValidateInput.validateId(ticketDTO.userId());
        ValidateInput.validateId(ticketDTO.flightId());
        ValidateInput.validateSeatClass(ticketDTO.seatClass());
        ValidateInput.validateNumOfSeats(ticketDTO.reservedSeats());

        // Retrieve the user
        Optional<User> optionalUser = userRepository.findById(ticketDTO.userId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }
        User user = optionalUser.get();

        // Retrieve the flight
        Optional<Flight> optionalFlight = flightRepository.findById(ticketDTO.flightId());
        if (optionalFlight.isEmpty()) {
            throw new IllegalArgumentException("Flight not found.");
        }
        Flight flight = optionalFlight.get();

        // Check if the user already has a reservation for the flight
        boolean alreadyReserved = reservationRepository.existsByUserIdAndFlightId(ticketDTO.userId(), ticketDTO.flightId());
        if (alreadyReserved) {
            throw new IllegalArgumentException("User has already reserved a seat on this flight.");
        }

        // Check seat availability
        if (ticketDTO.seatClass() == SeatClass.BUSINESS) {
            if (flight.getAvailableBusinessSeats() < ticketDTO.reservedSeats()) {
                throw new IllegalArgumentException("Not enough seats available for the reservation.");
            }
            else {
                flight.setAvailableBusinessSeats(flight.getAvailableBusinessSeats() - ticketDTO.reservedSeats());
                flightRepository.save(flight);
            }
        }
        else {
            if (flight.getAvailableEconomySeats() < ticketDTO.reservedSeats()) {
                throw new IllegalArgumentException("Not enough seats available for the reservation.");
            }
            else {
                flight.setAvailableEconomySeats(flight.getAvailableEconomySeats() - ticketDTO.reservedSeats());
                flightRepository.save(flight);
            }
        }

         // Create and save the reservation
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setFlight(flight);
        reservation.setSeatClass(ticketDTO.seatClass());
        reservation.setReservedSeats(ticketDTO.reservedSeats());

        reservationRepository.save(reservation);
    }
}
