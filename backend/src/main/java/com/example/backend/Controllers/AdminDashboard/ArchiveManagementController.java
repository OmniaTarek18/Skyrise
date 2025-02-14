package com.example.backend.Controllers.AdminDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.AdminDashboard.AdminFlightDTO;
import com.example.backend.DTOs.AdminDashboard.FlightFilterCriteria;
import com.example.backend.Services.AdminDashboard.ArchiveDisplayService;
import com.example.backend.Utilites.ValidateInput;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ArchiveManagementController {
    private final ArchiveDisplayService archiveDisplayService;

    @PostMapping("/archive")
    public ResponseEntity<PageResponse<AdminFlightDTO>> filter(@RequestBody FlightFilterCriteria flightFilterDTO,
            @RequestParam(defaultValue = "0") int pageNumber) {

        ValidateInput.validatePageNumber(pageNumber);
        PageResponse<AdminFlightDTO> page = archiveDisplayService.filterPastFlights(flightFilterDTO, pageNumber);
        return ResponseEntity.ok(page);

    }

}
