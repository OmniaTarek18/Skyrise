package com.example.backend.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.backend.DTOs.AirportDTO;
import com.example.backend.Services.AirportDisplayService;


@Controller
public class AirportDisplayController {

    private final AirportDisplayService airportDisplayService;

    public AirportDisplayController (AirportDisplayService airportDisplayService) {
        this.airportDisplayService = airportDisplayService;
    }
    
    @GetMapping("/airports")
    public ResponseEntity<List<AirportDTO>> getAllAirports() {

        List<AirportDTO> airports = airportDisplayService.getAllAirports();
        return ResponseEntity.ok(airports);
    }
    

}