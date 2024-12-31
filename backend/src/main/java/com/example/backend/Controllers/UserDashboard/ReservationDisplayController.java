package com.example.backend.Controllers.UserDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.ReservationDTO;
import com.example.backend.DTOs.ReservationFilterCriteria;
import com.example.backend.Services.ReservationDisplayService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/user")
public class ReservationDisplayController {
    private final ReservationDisplayService reservationService;

    public ReservationDisplayController(ReservationDisplayService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservedFlights")
    public ResponseEntity<PageResponse<ReservationDTO>> getFilteredReservedFlights(
            @Valid @RequestBody ReservationFilterCriteria filterCriteria,
            @RequestParam(defaultValue = "0") int pageNumber) {

        PageResponse<ReservationDTO> page = reservationService.filterReserved(filterCriteria, pageNumber);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/recentReservation/{userId}")
    public ResponseEntity<ReservationDTO> getMostRecentReservation(@PathVariable Integer userId) {
        ReservationDTO recentReservation = reservationService.getMostRecentReservation(userId);
        return ResponseEntity.ok(recentReservation);
    }

    @PutMapping("/updateDismissCount/{userId}/{flightId}")
    public ResponseEntity<String> updateDismissCount(@PathVariable Integer userId,
            @PathVariable Integer flightId,
            @RequestParam short newDismissCount) {
        reservationService.updateDismissCount(userId, flightId, newDismissCount);
        return ResponseEntity.ok("Dismiss count updated successfully.");
    }

}