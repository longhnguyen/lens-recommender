package com.longnguyen.lensrecommender.controller;

import com.longnguyen.lensrecommender.dto.LensResponse;
import com.longnguyen.lensrecommender.dto.RecommendationRequest;
import com.longnguyen.lensrecommender.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing endpoint for lens recommendations
 */
@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * Returns a ranked list of recommended lenses based on user input
     *
     * @param request user input containing camera and purpose
     * @return list of recommended lenses sorted by score (descending)
     */
    @PostMapping("/recommendations")
    public List<LensResponse> getRecommendations(@RequestBody RecommendationRequest request) {
        return this.recommendationService.recommendLenses(request);
    }
}