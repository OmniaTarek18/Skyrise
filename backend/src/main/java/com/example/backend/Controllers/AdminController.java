package com.example.backend.Controllers;

import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Services.AdminServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }


    @PutMapping(path = "upgrade")
    public ResponseEntity<String> upgradeUser(@RequestParam String email) {
        boolean flag = adminServices.upgradeUserToAdmin(email);
        if (flag) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }



    @PostMapping(path = "/addFlight")
    public ResponseEntity<Integer> addFlight(@RequestBody Flight flight) {
        int index = this.adminServices.createFlight(flight);
        return new ResponseEntity<>(index, HttpStatus.OK);
    }

    @PostMapping(path = "/addFlightLeg")
    public ResponseEntity<Boolean> addFLightLeg(@RequestBody FlightLeg flightLeg) {
        boolean flag = adminServices.attachFlightLegToAFlight(flightLeg);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

}
