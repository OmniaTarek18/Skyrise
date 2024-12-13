package com.example.backend.Controllers.UserDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.DTOs.TicketDTO;
import com.example.backend.Services.TicketReservationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class TicketReservationController {

    private final TicketReservationService ticketReservationService;

    public TicketReservationController(TicketReservationService ticketReservationService) {
        this.ticketReservationService = ticketReservationService;
    }

    @PostMapping("/bookTicket")
    public ResponseEntity<String> addReservation(@Valid @RequestBody TicketDTO ticketDTO) {
        ticketReservationService.reserveTicket(ticketDTO);

        return ResponseEntity.ok("Booking tickets is done successfully.");
    }

    @GetMapping("/deleteTicket")
    public ResponseEntity<String> deleteReservation(@RequestParam Integer flightId, @RequestParam Integer userId) {
        ticketReservationService.deleteTicket(flightId, userId);
        return ResponseEntity.ok("Deleting tickets is done successfully.");
    }
    
}
