package com.example.backend.Services;

import org.springframework.stereotype.Service;

import com.example.backend.Repositories.FeedbackRepository;

@Service
public class FeedbackDisplayService {
    
    private final FeedbackRepository feedbackRepository;

    public FeedbackDisplayService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
}
