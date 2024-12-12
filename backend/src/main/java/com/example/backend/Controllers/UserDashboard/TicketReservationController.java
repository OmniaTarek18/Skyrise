package com.example.backend.Controllers.UserDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.DTOs.TicketDTO;
import com.example.backend.Services.TicketReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/user")
public class TicketReservationController {

    private final TicketReservationService ticketReservationService;

    public TicketReservationController(TicketReservationService ticketReservationService) {
        this.ticketReservationService = ticketReservationService;
    }

    @PostMapping("/ticket")
    public ResponseEntity<String> postMethodName(@RequestBody TicketDTO ticketDTO) {
        ticketReservationService.reserveTicket(ticketDTO);

        return ResponseEntity.ok("Booking tickets is done successfully.");
    }
}
