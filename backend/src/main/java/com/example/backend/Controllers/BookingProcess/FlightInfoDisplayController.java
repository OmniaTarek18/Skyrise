package com.example.backend.Controllers.BookingProcess;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.backend.Services.BookingProcess.FlightLegDisplayService;
import com.example.backend.DTOs.BookingDTOs.FlightLegDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightInfoDisplayController {
    private final FlightLegDisplayService flightLegDisplayService;

    @GetMapping("/{id}")
    public ResponseEntity<List<FlightLegDTO>> getAllFlightLegs(@PathVariable Integer id) {
        List<FlightLegDTO> listOfFlightLegs = flightLegDisplayService.getAllFlightLegs(id);
        return ResponseEntity.ok(listOfFlightLegs);
    }

}
