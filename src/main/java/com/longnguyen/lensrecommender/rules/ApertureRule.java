package com.longnguyen.lensrecommender.rules;

import com.longnguyen.lensrecommender.model.entity.Camera;
import com.longnguyen.lensrecommender.model.entity.Lens;
import com.longnguyen.lensrecommender.profile.PurposeProfile;
import org.springframework.stereotype.Component;

@Component
public class ApertureRule implements RecommendationRule
{
    @Override
    public RuleScore score(Camera camera, Lens lens, PurposeProfile profile) {

        // Lens aperture
        double aperture = lens.getMaxAperture();

        double baseScore;
        switch (profile.getAperturePreference()) {

            // ----------------------------------------
            // 1. Wide aperture preferred
            // ----------------------------------------
            case WIDE -> {
                if (aperture <= 1.4) baseScore = 10;
                else if (aperture <= 1.8) baseScore = 9;
                else if (aperture <= 2.0) baseScore = 8;
                else if (aperture <= 2.8) baseScore = 6;
                else if (aperture <= 4.0) baseScore = 4;
                else if (aperture <= 5.6) baseScore = 2;
                else baseScore = 1;
            }

            // ----------------------------------------
            // 2. Any aperture
            // ----------------------------------------
            case ANY -> {
                if (aperture <= 2.8) baseScore = 10;
                else if (aperture <= 4.0) baseScore = 9;
                else if (aperture <= 5.6) baseScore = 8;
                else if (aperture <= 8.0) baseScore = 7;
                else baseScore = 6;
            }

            default -> baseScore = 5;
        }

        int weightedScore = applyWeight((int) Math.round(baseScore), profile.getApertureWeight());

        return new RuleScore(ScoreType.APERTURE, weightedScore);
    }
}