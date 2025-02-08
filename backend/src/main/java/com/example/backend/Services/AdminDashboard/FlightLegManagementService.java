package com.example.backend.Services.AdminDashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.DTOs.AdminDashboard.FlightLegAddDTO;
import com.example.backend.DTOs.AdminDashboard.FlightLegUpdateDTO;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightLegManagementService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final FlightLegRepository flightLegRepository;

    public boolean updateFlightLegs(List<FlightLegUpdateDTO> flightLegs, Integer flightId) {

        if (flightRepository.existsById(flightId)) {
            Flight flight = flightRepository.findFlightByFlightId(flightId).get();
            List<FlightLeg> flightLegsList = flight.getFlightLegs();

            boolean isChanged = false;
            for (int i = 0; i < flightLegs.size(); i++) {
                FlightLegUpdateDTO flightLegDTO = flightLegs.get(i);
                FlightLeg flightLeg = flightLegsList.get(i);

                if (flightLeg.getDepartureTime().equals(flightLegDTO.departureTime()) &&
                        flightLeg.getArrivalTime().equals(flightLegDTO.arrivalTime()) &&
                        flightLeg.getDepartureAirport().getId().equals(flightLegDTO.departureAirportId()) &&
                        flightLeg.getArrivalAirport().getId().equals(flightLegDTO.arrivalAirportId())) {
                    isChanged = isChanged || false;
                } else {
                    isChanged = isChanged || true;
                    flightLeg.setDepartureTime(flightLegDTO.departureTime());
                    flightLeg.setArrivalTime(flightLegDTO.arrivalTime());

                    flightLeg.setDepartureAirport(airportRepository.findById(flightLegDTO.departureAirportId()).get());
                    flightLeg.setArrivalAirport(airportRepository.findById(flightLegDTO.arrivalAirportId()).get());
                    flightLegRepository.save(flightLeg);
                }

            }
            if (isChanged) {
                flightRepository.save(flight);
            }
            return isChanged;
        } else {
            throw new RuntimeException("Flight doesn't exist");
        }
    }

    public List<FlightLeg> addFlightLegs(List<FlightLegAddDTO> flightLegs, Integer flightId) {
        if (!flightRepository.existsById(flightId)) {
            throw new RuntimeException("Flight doesn't exist");
        }

        Flight flight = flightRepository.findFlightByFlightId(flightId).get();
        List<FlightLeg> flightLegsList = new ArrayList<>();
        for (int i = 0; i < flightLegs.size(); i++) {
            FlightLegAddDTO flightLegDTO = flightLegs.get(i);
            FlightLeg flightLeg = new FlightLeg();
            flightLeg.setFlightLegId(i+1);
            flightLeg.setDepartureTime(flightLegDTO.departureTime());
            flightLeg.setArrivalTime(flightLegDTO.arrivalTime());
            flightLeg.setDepartureAirport(airportRepository.findById(flightLegDTO.departureAirportId()).get());
            flightLeg.setArrivalAirport(airportRepository.findById(flightLegDTO.arrivalAirportId()).get());
            flightLeg.setFlight(flight);
            flightLegsList.add(flightLeg);
        }
        flightLegRepository.saveAll(flightLegsList);
        return flightLegsList;
    }
}
