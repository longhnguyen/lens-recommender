package com.longnguyen.lensrecommender.controller;

import com.longnguyen.lensrecommender.dto.LensResponse;
import com.longnguyen.lensrecommender.dto.RecommendationRequest;
import com.longnguyen.lensrecommender.service.RecommendationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController
{
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recommendations")
    public List<LensResponse> getRecommendations(@RequestBody RecommendationRequest request) {
        return this.recommendationService.recommendLenses(request);
    }
}