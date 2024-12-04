package com.example.backend.Controllers.AdminDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.DTOs.FeedbackFilterCriteria;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Services.FeedbackDisplayService;

@Controller
@RequestMapping("/admin")
public class FeedbackDisplayController {

    private final FeedbackDisplayService feedbackDisplayService;

    public FeedbackDisplayController(FeedbackDisplayService feedbackDisplayService) {
        this.feedbackDisplayService = feedbackDisplayService;
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Short> getAverageRating() {
        short averageRating = feedbackDisplayService.getAverageRating();
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/feedback")
    public ResponseEntity<PageResponse<FeedbackDTO>> getAllFeedback(@RequestParam(defaultValue = "0") int pageNumber) {
        PageResponse<FeedbackDTO> page = feedbackDisplayService.getAll(pageNumber);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/feedback")
    public ResponseEntity<PageResponse<FeedbackDTO>> filterFeedback(
            @RequestBody FeedbackFilterCriteria feedbackFilterDTO, @RequestParam(defaultValue = "0") int pageNumber) {
        PageResponse<FeedbackDTO> page = feedbackDisplayService.filterFeedback(feedbackFilterDTO, pageNumber);
        return ResponseEntity.ok(page);
    }

}
