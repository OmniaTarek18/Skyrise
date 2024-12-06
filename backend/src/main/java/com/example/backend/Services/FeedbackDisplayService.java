package com.example.backend.Services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.backend.Repositories.FeedbackRepository;
import com.example.backend.Specifications.FeedbackSpecifications;
import com.example.backend.Utilites.Utilities;
import com.example.backend.Utilites.ValidateInput;
import lombok.RequiredArgsConstructor;
import com.example.backend.DTOMappers.FeedbackMapper;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.DTOs.FeedbackFilterCriteria;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Entities.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
@Service
public class FeedbackDisplayService {

    private final FeedbackRepository feedbackRepository;

    public short getAvergaeRating(){
        Double averageRating = feedbackRepository.getAvgRating();
        return averageRating == null ? 0 : averageRating.shortValue();
    }

    public PageResponse<FeedbackDTO> getAll(int pageNumber) {

        ValidateInput.validatePageNumber(pageNumber);
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Feedback> page = feedbackRepository.findAll(pageable);
        Page<FeedbackDTO> pageDTO = page.map(FeedbackMapper::toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);

    }

public PageResponse<FeedbackDTO> filterFeedback(FeedbackFilterCriteria feedbackFilterDTO, int pageNumber) {
    ValidateInput.validatePageNumber(pageNumber);

    Specification<Feedback> spec = Specification.where(null);

    // add specifications based on filter criteria (only if they are not null)
    if (feedbackFilterDTO.stars() > 0) {
        spec = spec.and(FeedbackSpecifications.containsStars(feedbackFilterDTO.stars()));
    }

        spec = spec.and(FeedbackSpecifications.containsService(feedbackFilterDTO.service()));

        spec = spec.and(FeedbackSpecifications.containsComfort(feedbackFilterDTO.comfort()));

        spec = spec.and(FeedbackSpecifications.containsPunctuality(feedbackFilterDTO.punctuality()));
    

        spec = spec.and(FeedbackSpecifications.containsCleanliness(feedbackFilterDTO.cleanliness()));
  
        spec = spec.and(FeedbackSpecifications.containsFoodAndBeverage(feedbackFilterDTO.foodAndBeverage()));
    

    String sortDirection = feedbackFilterDTO.direction();
    Sort sort = Utilities.sort(sortDirection, "dateOfCreation");

    Pageable pageable = PageRequest.of(pageNumber, 10, sort );
    
    Page<Feedback> page = feedbackRepository.findAll(spec, pageable);
    Page<FeedbackDTO> pageDTO = page.map(FeedbackMapper::toDTO);
    return PageResponseMapper.toPageResponse(pageDTO);
}

}
