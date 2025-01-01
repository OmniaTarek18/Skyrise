package com.example.backend.Services.AdminDashboard;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.DTOs.NotificationDTO;
import com.example.backend.DTOs.AdminDashboard.FlightUpdateDTO;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Enums.MessageTitle;
import com.example.backend.Enums.Status;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Services.NotificationService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightManagmentService {

    private final FlightRepository flightRepository;
    private final NotificationService notificationService;
    private final FlightLegManagementService flightLegManagementService;

    public void deleteFlight(int id) {
        if (flightRepository.existsById(id))
            flightRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Flight doesn't exist");
    }

    public void cancelFlight(int id) {
        if (flightRepository.existsById(id)) {
            Flight flight = flightRepository.findById(id).get();
            List<FlightLeg> flightLegs = flight.getFlightLegs();

            String source = flightLegs.get(0).getDepartureAirport().getAirportCity();
            String destination = flightLegs.get(flight.getFlightLegs().size() - 1).getArrivalAirport().getAirportCity();

            flight.setCancel(true);
            flightRepository.save(flight);

            List<Integer> userIds = getReservationUserIds(flight);
            String message = message(flight.getDepartureDate(), source, destination, true);
            NotificationDTO notification = new NotificationDTO(null, null, flight.getId(), MessageTitle.CANCELLED, message, Status.UNSEEN);
            notificationService.addNotificationToConcernedUsers(notification, userIds);
        } else
            throw new EntityNotFoundException("Flight doesn't exist");
    }

    public void updateFlight(FlightUpdateDTO flightDTO) {

        if (flightRepository.existsById(flightDTO.id())) {
            Flight flight = flightRepository.findById(flightDTO.id()).get();
            List<FlightLeg> flightLegs = flight.getFlightLegs();

            if (flight.isCancel())
                throw new IllegalArgumentException("Flight is cancelled");

            String source = flightLegs.get(0).getDepartureAirport().getAirportCity();
            String destination = flightLegs.get(flight.getFlightLegs().size() - 1).getArrivalAirport().getAirportCity();

            String message = message(flight.getDepartureDate(), source, destination, false);
            
            boolean isChanged = true;
            if (flight.getArrivalDate().equals(flightDTO.arrivalDate()) && flight.getDepartureDate().equals(flightDTO.departureDate()))
                isChanged = false;
            else {
                flight.setArrivalDate(flightDTO.arrivalDate());
                flight.setDepartureDate(flightDTO.departureDate());
            }

            isChanged = (isChanged || flightLegManagementService.updateFlightLegs(flightDTO.flightLegs(), flight.getId()));

            if (isChanged) {
                flightRepository.save(flight);
                List<Integer> userIds = getReservationUserIds(flight);
                NotificationDTO notification = new NotificationDTO(null, null, flight.getId(), MessageTitle.UPDATED, message, Status.UNSEEN);
                notificationService.addNotificationToConcernedUsers(notification, userIds);
            } else
                throw new IllegalArgumentException("No changes made");
        } else
            throw new EntityNotFoundException("Flight doesn't exist");
    }

    private String message(LocalDate departureDate, String source, String destination, boolean cancel) {
        String method = cancel ? "cancelled" : "updated";
        return "Flight with departure date: " + departureDate + 
                                " , source: " + source +
                                " , and destination: " + destination + 
                                " has been " + method;
    }

    private List<Integer> getReservationUserIds(Flight flight) {
        return flight.getReservations().stream()
            .map(reservation -> reservation.getUser().getUserId())
            .collect(Collectors.toList());
    }
}
