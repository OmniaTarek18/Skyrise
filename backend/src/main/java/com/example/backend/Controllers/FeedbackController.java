package com.example.backend.Controllers;

import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.Services.FeedbackServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping
@CrossOrigin
public class FeedbackController {
    private final FeedbackServices feedbackServices;

    @PostMapping("add/feedback")
    public ResponseEntity<String> addFeedback(@RequestBody FeedbackDTO feedback) {
        try {
            feedbackServices.addFeedback(feedback);
            return ResponseEntity.ok().body("Feedback has been added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
