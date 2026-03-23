package com.longnguyen.lensrecommender.service;

import com.longnguyen.lensrecommender.dto.LensResponse;
import com.longnguyen.lensrecommender.dto.RecommendationRequest;
import com.longnguyen.lensrecommender.dto.ScoredLens;
import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.enums.CameraBrand;
import com.longnguyen.lensrecommender.profile.PurposeProfile;
import com.longnguyen.lensrecommender.repository.CameraAliasRepository;
import com.longnguyen.lensrecommender.repository.CameraRepository;
import com.longnguyen.lensrecommender.repository.LensRepository;
import com.longnguyen.lensrecommender.rules.ScoreType;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for generating lens recommendations.
 *
 * Combines:
 * - Camera resolution (direct or via alias)
 * - Compatibility filtering
 * - Rule-based scoring
 */
@Service
public class RecommendationService {

    /** Repository for resolving Camera data */
    private final CameraRepository cameraRepository;

    /** Repository for resolving Camera Alias data */
    private final CameraAliasRepository cameraAliasRepository;

    /** Repository for resolving Lens data */
    private final LensRepository lensRepository;

    /** Service that checks compatibility between lenses and cameras */
    private final CompatibilityService compatibilityService;

    /** Orchestrator of all rules used in the score calculations */
    private final RulesEngine rulesEngine;

    /**
     * Dependency Injection via constructor method
     */
    public RecommendationService(CameraRepository cameraRepository,
            CameraAliasRepository cameraAliasRepository,
            LensRepository lensRepository,
            CompatibilityService compatibilityService,
            RulesEngine rulesEngine) {
        this.cameraRepository = cameraRepository;
        this.cameraAliasRepository = cameraAliasRepository;
        this.lensRepository = lensRepository;
        this.compatibilityService = compatibilityService;
        this.rulesEngine = rulesEngine;
    }

    /**
     * Generates a ranked list of lens recommendations for a given request.
     *
     * Workflow:
     * 1. Resolve camera by model or alias
     * 2. Filter lenses compatible with the camera
     * 3. Score lenses using the rules engine
     * 4. Sort by total score (descending)
     * 5. Return top results
     *
     * @param request user input defining camera and intended use
     * @return top matching lenses sorted by score
     */
    public List<LensResponse> recommendLenses(RecommendationRequest request) {

        CameraBrand cameraBrand = request.cameraBrand();
        String cameraModel = request.cameraModel();
        PurposeProfile purposeProfile = request.purpose();

        // Resolve camera either directly or via alias fallback
        final Camera camera = cameraRepository.findByModel(cameraModel)
                .orElseGet(() -> cameraAliasRepository.findByAliasAndCamera_Brand(
                                cameraModel, cameraBrand)
                        .orElseThrow(() -> new RuntimeException("Camera not found"))
                        .getCamera());

        return lensRepository.findAll().stream()
                // Filter lenses compatible with a camera's mount type and sensor size
                .filter(lens -> compatibilityService.isCameraCompatible(camera, lens))
                // Score lenses using rule engine based on intended purpose
                .map(lens -> {
                    Map<ScoreType, Integer> scores = rulesEngine.score(camera, lens, purposeProfile);
                    return new ScoredLens(lens, scores.get(ScoreType.FOCAL_LENGTH),
                            scores.get(ScoreType.APERTURE),
                            scores.get(ScoreType.USABILITY));
                })
                // Convert to API response
                .map(LensResponse::fromLens)
                // Sort lenses by descending score
                .sorted(Comparator.comparingInt(LensResponse::totalScore).reversed())
                // Only return top 10 matches
                .limit(10)
                .toList();
    }
}