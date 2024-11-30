package com.example.backend.Services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.backend.Repositories.FeedbackRepository;
import com.example.backend.Specifications.FeedbackSpecifications;
import com.example.backend.Utilites.Utilities;
import com.example.backend.Utilites.ValidateInput;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.DTOs.FeedbackFilterCriteria;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Entities.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Service
public class FeedbackDisplayService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackDisplayService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public PageResponse<Feedback> getAll(int pageNumber) {

        ValidateInput.validatePageNumber(pageNumber);
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Feedback> page = feedbackRepository.findAll(pageable);

        return PageResponseMapper.toPageResponse(page);
    }

    public PageResponse<Feedback> filterFeedback(FeedbackFilterCriteria feedbackFilterDTO, int pageNumber){
        ValidateInput.validatePageNumber(pageNumber);
        Specification<Feedback> spec = Specification.where(null);

        spec = spec.and(FeedbackSpecifications.containsStars(feedbackFilterDTO.stars()));
        spec = spec.and(FeedbackSpecifications.containsService(feedbackFilterDTO.service()));
        spec = spec.and(FeedbackSpecifications.containsComfort(feedbackFilterDTO.comfort()));
        spec = spec.and(FeedbackSpecifications.containsPunctuality(feedbackFilterDTO.punctuality()));
        spec = spec.and(FeedbackSpecifications.containsCleanliness(feedbackFilterDTO.cleanliness()));
        spec = spec.and(FeedbackSpecifications.containsFoodAndBeverage(feedbackFilterDTO.foodAndBeverage()));
        
        Sort sort = Utilities.sort(feedbackFilterDTO.direction(), "dateOfCreation");
        Pageable pageable = PageRequest.of(pageNumber, 10, sort);
        Page<Feedback> page = feedbackRepository.findAll(spec, pageable);

        return PageResponseMapper.toPageResponse(page);
    }
}
