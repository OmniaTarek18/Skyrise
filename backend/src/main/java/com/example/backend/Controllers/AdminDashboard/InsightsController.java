package com.example.backend.Controllers.AdminDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.DTOs.InsightsDTO;
import com.example.backend.Services.InsightsService;

@Controller
@RequestMapping("/admin")
public class InsightsController {
    
    private final InsightsService insightsService;

    public InsightsController (InsightsService insightsService) {
        this.insightsService = insightsService;
    }

    @GetMapping("/insights")
    public ResponseEntity<InsightsDTO> getInsights () {
        InsightsDTO dto = insightsService.getInsights();
        return ResponseEntity.ok(dto);
    }
}
