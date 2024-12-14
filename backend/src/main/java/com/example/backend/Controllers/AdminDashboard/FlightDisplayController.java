package com.example.backend.Controllers.AdminDashboard;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.AdminDashboard.AdminFlightDTO;
import com.example.backend.DTOs.AdminDashboard.FlightFilterCriteria;
import com.example.backend.Services.AdminDashboard.FlightDisplayService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class FlightDisplayController {

    private final FlightDisplayService flightService;

    @PostMapping("/flights")
    public ResponseEntity<PageResponse<AdminFlightDTO>> filter(@RequestBody FlightFilterCriteria flightFilterDTO,
            @RequestParam(defaultValue = "0") int pageNumber) {

        PageResponse<AdminFlightDTO> page = flightService.filterFlights(flightFilterDTO, pageNumber);
        return ResponseEntity.ok(page);

    }

}
