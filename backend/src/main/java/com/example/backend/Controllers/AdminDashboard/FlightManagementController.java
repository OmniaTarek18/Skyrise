package com.example.backend.Controllers.AdminDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.DTOs.AdminDashboard.FlightUpdateDTO;
import com.example.backend.Services.AdminDashboard.FlightManagmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class FlightManagementController {
    private final FlightManagmentService flightManagmentService;

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable int id) {
        flightManagmentService.deleteFlight(id);
        return ResponseEntity.ok("The flight has been deleted successfully.");
    }

    @PutMapping("/flights/cancel/{id}")
    public ResponseEntity<String> cancelFlight(@PathVariable int id) {
        flightManagmentService.cancelFlight(id);
        return ResponseEntity.ok("The flight has been cancelled successfully.");
    }

    @PutMapping("/flights/update")
    public ResponseEntity<String> updateFlight(@RequestBody FlightUpdateDTO flightDTO) {
        flightManagmentService.updateFlight(flightDTO);
        return ResponseEntity.ok("The flight has been updated successfully.");
    }
}
