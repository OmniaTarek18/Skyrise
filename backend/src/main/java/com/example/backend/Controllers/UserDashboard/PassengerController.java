package com.example.backend.Controllers.UserDashboard;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.backend.DTOs.PassengerDTO;
import com.example.backend.Services.PassengerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/passengers")
    public ResponseEntity<String> addPassengers(@RequestBody List<@Valid PassengerDTO> passengers, 
            @RequestParam Integer userId,
            @RequestParam Integer flightId) {
                
        passengerService.addPassengers(passengers, userId, flightId);
        
        return ResponseEntity.ok("Passenger added successfully.");
    }

}
