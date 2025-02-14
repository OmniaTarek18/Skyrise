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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*")
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

}