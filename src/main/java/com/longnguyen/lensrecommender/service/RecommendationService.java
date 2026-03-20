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

@Service
public class RecommendationService
{
    /** Dependencies */
    private final CameraRepository cameraRepository;
    private final CameraAliasRepository cameraAliasRepository;
    private final LensRepository lensRepository;
    private final CompatibilityService compatibilityService;
    private final RulesEngine rulesEngine;

    /** Injected dependencies via constructor */
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

    /** Logic for lens recommendations */
    public List<LensResponse> recommendLenses(RecommendationRequest request) {

        // Request fields
        CameraBrand cameraBrand = request.cameraBrand();
        String cameraModel = request.cameraModel();
        PurposeProfile purposeProfile = request.purpose();

        // Fetch camera from database
        final Camera camera = cameraRepository.findByModel(cameraModel)
                .orElseGet(() -> cameraAliasRepository.findByAliasAndCamera_Brand(
                                cameraModel, cameraBrand)
                        .orElseThrow(() -> new RuntimeException("Camera not found"))
                        .getCamera());

        return lensRepository.findAll().stream()
                // Filter lenses compatible with camera/mount
                .filter(lens -> compatibilityService.isCameraCompatible(camera, lens))
                // Calculate scores for lenses
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
                // Only return top 5 matches
                .limit(10)
                .toList();
    }
}